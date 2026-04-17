package com.groupeisi.consultation_ms.controller;

import com.groupeisi.common_ms.dto.ConsultationDTO;
import com.groupeisi.consultation_ms.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public ResponseEntity<List<ConsultationDTO>> getAllConsultations() {
        return ResponseEntity.ok(consultationService.getAllConsultations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable Long id) {
        return ResponseEntity.ok(consultationService.getConsultationById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ConsultationDTO>> getConsultationsByPatientId(@PathVariable Long patientId) {
        return ResponseEntity.ok(consultationService.getConsultationsByPatientId(patientId));
    }

    @PostMapping
    public ResponseEntity<ConsultationDTO> createConsultation(@RequestBody ConsultationDTO consultationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultationService.createConsultation(consultationDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDTO> updateConsultation(@PathVariable Long id, @RequestBody ConsultationDTO consultationDTO) {
        return ResponseEntity.ok(consultationService.updateConsultation(id, consultationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
        return ResponseEntity.noContent().build();
    }
}