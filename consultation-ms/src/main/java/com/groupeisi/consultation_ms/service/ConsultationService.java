package com.groupeisi.consultation_ms.service;

import com.groupeisi.common_ms.dto.ConsultationDTO;
import com.groupeisi.common_ms.dto.NotificationDTO;
import com.groupeisi.common_ms.dto.PatientDTO;
import com.groupeisi.common_ms.exception.EntityNotFoundException;
import com.groupeisi.consultation_ms.feign.PatientClient;
import com.groupeisi.consultation_ms.kafka.ConsultationKafkaProducer;
import com.groupeisi.consultation_ms.mapping.ConsultationMapping;
import com.groupeisi.consultation_ms.repository.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapping consultationMapping;
    private final PatientClient patientClient;
    private final ConsultationKafkaProducer kafkaProducer;

    public List<ConsultationDTO> getAllConsultations() {
        return consultationRepository.findAll()
                .stream()
                .map(consultationMapping::toDTO)
                .collect(Collectors.toList());
    }

    public ConsultationDTO getConsultationById(Long id) {
        return consultationRepository.findById(id)
                .map(consultationMapping::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Consultation avec id " + id + " non trouvée"));
    }

    public List<ConsultationDTO> getConsultationsByPatientId(Long patientId) {
        return consultationRepository.findByPatientId(patientId)
                .stream()
                .map(consultationMapping::toDTO)
                .collect(Collectors.toList());
    }

    public ConsultationDTO createConsultation(ConsultationDTO consultationDTO) {
        // Communication synchrone : vérifier que le patient existe
        PatientDTO patient = patientClient.getPatientById(consultationDTO.getPatientId());
        log.info("Patient trouvé : {} {}", patient.getNom(), patient.getPrenom());

        ConsultationDTO saved = consultationMapping.toDTO(
                consultationRepository.save(consultationMapping.toEntity(consultationDTO))
        );

        // Communication asynchrone : envoyer notification via Kafka
        NotificationDTO notification = new NotificationDTO();
        notification.setEmail(patient.getEmail());
        notification.setMessage("Consultation créée pour " + patient.getNom() + " " + patient.getPrenom()
                + " le " + consultationDTO.getDateConsultation());
        notification.setType("CONSULTATION_CREEE");
        kafkaProducer.sendNotification(notification);

        return saved;
    }

    public ConsultationDTO updateConsultation(Long id, ConsultationDTO consultationDTO) {
        return consultationRepository.findById(id)
                .map(consultation -> {
                    consultation.setMotif(consultationDTO.getMotif());
                    consultation.setDateConsultation(consultationDTO.getDateConsultation());
                    consultation.setStatut(consultationDTO.getStatut());
                    consultation.setPatientId(consultationDTO.getPatientId());
                    return consultationMapping.toDTO(consultationRepository.save(consultation));
                })
                .orElseThrow(() -> new EntityNotFoundException("Consultation avec id " + id + " non trouvée"));
    }

    public void deleteConsultation(Long id) {
        if (!consultationRepository.existsById(id)) {
            throw new EntityNotFoundException("Consultation avec id " + id + " non trouvée");
        }
        consultationRepository.deleteById(id);
    }
}