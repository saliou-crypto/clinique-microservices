package com.groupeisi.consultation_ms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consultations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String motif;

    @Column(nullable = false)
    private String dateConsultation;

    @Column(nullable = false)
    private String statut;

    @Column(nullable = false)
    private Long patientId;
}