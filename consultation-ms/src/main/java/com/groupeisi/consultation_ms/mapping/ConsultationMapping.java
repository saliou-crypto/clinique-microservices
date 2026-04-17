package com.groupeisi.consultation_ms.mapping;

import com.groupeisi.common_ms.dto.ConsultationDTO;
import com.groupeisi.consultation_ms.entities.Consultation;
import org.mapstruct.Mapper;

@Mapper
public interface ConsultationMapping {
    ConsultationDTO toDTO(Consultation consultation);
    Consultation toEntity(ConsultationDTO consultationDTO);
}