package com.example.patientservice.controllers;


import com.example.patientservice.DTO.patientResponseDTO;
import com.example.patientservice.service.patientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class patientController {

    private final patientService patientService;

    public patientController(patientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<patientResponseDTO>> getAllPatients() {
        List<patientResponseDTO> patients=patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }
}
