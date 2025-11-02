package com.example.patientservice.service;

import com.example.patientservice.DTO.patientRequestDTO;
import com.example.patientservice.DTO.patientResponseDTO;
import com.example.patientservice.exception.EmailAlreadyExistsException;
import com.example.patientservice.exception.patientNotFoundException;
import com.example.patientservice.mapper.patientMapper;
import com.example.patientservice.model.patient;
import com.example.patientservice.repository.patientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    public patientResponseDTO updatePatient(UUID id,patientRequestDTO patientRequestDTO) {

        patient patient= patientRepository.findById(id).orElseThrow(
                ()-> new patientNotFoundException("Patient not found" + id));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email " + "already exists"
                    + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        patient updatedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(updatedPatient);
    }
}
