package com.groupeisi.common_ms.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {
    private Long id;
    private String motif;
    private String dateConsultation;
    private String statut;
    private Long patientId;
}