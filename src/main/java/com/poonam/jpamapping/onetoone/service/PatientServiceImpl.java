package com.poonam.jpamapping.onetoone.service;

import com.poonam.jpamapping.onetoone.dto.PatientRequestDto;
import com.poonam.jpamapping.onetoone.dto.PatientResponseDto;
import com.poonam.jpamapping.onetoone.entity.MedicalRecordEntity;
import com.poonam.jpamapping.onetoone.entity.PatientEntity;
import com.poonam.jpamapping.onetoone.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;

    @Override
    public PatientResponseDto createPatient(PatientRequestDto requestDto) {

        // Step 1: Form se MedicalRecord file banao
        MedicalRecordEntity medicalRecord = MedicalRecordEntity.builder()
                .bloodGroup(requestDto.getBloodGroup())
                .diagnosis(requestDto.getDiagnosis())
                .build();

        // Step 2: Patient file banao aur MedicalRecord usse jodo
        PatientEntity patient = PatientEntity.builder()
                .name(requestDto.getName())
                .age(requestDto.getAge())
                .medicalRecord(medicalRecord)
                .build();

        // Step 3: DB me save karo
        PatientEntity savePatient = patientRepository.save(patient);

        // Step 4: Slip (response) banao aur wapas do
       return mapToResponseDto(patient);
    }

    //==============================
    //   Read Patients By Id
    //==============================
    @Override
    public PatientResponseDto getPatientById(Long id) {
        PatientEntity patient = patientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Patient not found with id : " + id));

       return mapToResponseDto(patient);
    }


    // Entity ko Response DTO me badalne wala helper (dono methods use karenge)
     private  PatientResponseDto mapToResponseDto(PatientEntity patient){
        return PatientResponseDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .age(patient.getAge())
                .bloodGroup(patient.getMedicalRecord().getBloodGroup())
                .diagnosis(patient.getMedicalRecord().getDiagnosis())
                .build();
     }
}
