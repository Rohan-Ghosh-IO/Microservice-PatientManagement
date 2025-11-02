package com.example.patientservice.controllers;


import com.example.patientservice.DTO.patientRequestDTO;
import com.example.patientservice.DTO.patientResponseDTO;
import com.example.patientservice.DTO.validators.createPatientValidationGroup;
import com.example.patientservice.service.patientService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class patientController {

    private final patientService patientService;

    public patientController(patientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<patientResponseDTO>> getAllPatients() {
        List<patientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }


    @PostMapping("create")
    public ResponseEntity<patientResponseDTO> createPatient(
            @Validated({Default.class, createPatientValidationGroup.class})
            @RequestBody patientRequestDTO patientRequestDTO) {

        patientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<patientResponseDTO> updatePatient(
            @PathVariable UUID id,
            @Validated({Default.class})
            @RequestBody patientRequestDTO patientRequestDTO) {

        patientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);

    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable UUID id) {

        patientService.deletePatient(id);
        return  ResponseEntity.noContent().build();
    }
}