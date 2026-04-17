package com.groupeisi.patient_ms.mapping;
import com.groupeisi.common_ms.dto.PatientDTO;
import com.groupeisi.patient_ms.entities.Patient;
import org.mapstruct.Mapper;

@Mapper
public interface PatientMapping {
    PatientDTO toDTO(Patient patient);
    Patient toEntity(PatientDTO patientDTO);
}