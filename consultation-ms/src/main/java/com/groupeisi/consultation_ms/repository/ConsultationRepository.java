package com.groupeisi.consultation_ms.repository;

import com.groupeisi.consultation_ms.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    java.util.List<Consultation> findByPatientId(Long patientId);
}