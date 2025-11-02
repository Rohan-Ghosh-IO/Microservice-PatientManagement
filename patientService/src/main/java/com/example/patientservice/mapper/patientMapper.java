package com.example.patientservice.mapper;

import com.example.patientservice.DTO.patientRequestDTO;
import com.example.patientservice.DTO.patientResponseDTO;
import com.example.patientservice.model.patient;

import java.time.LocalDate;

public class patientMapper {

    public static patientResponseDTO toDTO(patient patient) {
        patientResponseDTO patientDTO = new patientResponseDTO();

        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());

        return  patientDTO;
    }


    public static patient toModel(patientRequestDTO patientRequestDTO) {
        patient patient = new patient();
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patient;
    }
}
