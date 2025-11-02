package com.example.patientservice.service;

import com.example.patientservice.DTO.patientRequestDTO;
import com.example.patientservice.DTO.patientResponseDTO;
import com.example.patientservice.exception.EmailAlreadyExistsException;
import com.example.patientservice.mapper.patientMapper;
import com.example.patientservice.model.patient;
import com.example.patientservice.repository.patientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class patientService {
    private final patientRepository patientRepository;

    public patientService(patientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<patientResponseDTO> getPatients() {
        List<patient> patients = patientRepository.findAll();

        return patients.stream().map(patientMapper::toDTO).toList();
    }

    public patientResponseDTO createPatient(patientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email " + "already exists"
                            + patientRequestDTO.getEmail());
        }

        patient newPatient = patientRepository.save(
                patientMapper.toModel(patientRequestDTO)
        );
        return patientMapper.toDTO(newPatient);
    }
}
