package com.groupeisi.patient_ms.service;

import com.groupeisi.common_ms.dto.PatientDTO;
import com.groupeisi.common_ms.exception.EntityExistsException;
import com.groupeisi.common_ms.exception.EntityNotFoundException;
import com.groupeisi.patient_ms.mapping.PatientMapping;
import com.groupeisi.patient_ms.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapping patientMapping;

    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapping::toDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(patientMapping::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Patient avec id " + id + " non trouvé"));
    }

    public PatientDTO createPatient(PatientDTO patientDTO) {
        if (patientRepository.existsByEmail(patientDTO.getEmail())) {
            throw new EntityExistsException("Patient avec email " + patientDTO.getEmail() + " existe déjà");
        }
        return patientMapping.toDTO(patientRepository.save(patientMapping.toEntity(patientDTO)));
    }

    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setNom(patientDTO.getNom());
                    patient.setPrenom(patientDTO.getPrenom());
                    patient.setEmail(patientDTO.getEmail());
                    patient.setTelephone(patientDTO.getTelephone());
                    patient.setAdresse(patientDTO.getAdresse());
                    return patientMapping.toDTO(patientRepository.save(patient));
                })
                .orElseThrow(() -> new EntityNotFoundException("Patient avec id " + id + " non trouvé"));
    }

    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new EntityNotFoundException("Patient avec id " + id + " non trouvé");
        }
        patientRepository.deleteById(id);
    }
}