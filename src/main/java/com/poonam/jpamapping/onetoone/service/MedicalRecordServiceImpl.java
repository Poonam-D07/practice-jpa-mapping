package com.poonam.jpamapping.onetoone.service;

import com.poonam.jpamapping.onetoone.dto.MedicalRecordResponseDto;
import com.poonam.jpamapping.onetoone.entity.MedicalRecordEntity;
import com.poonam.jpamapping.onetoone.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecordResponseDto getMedicalRecordById(Long id) {

        MedicalRecordEntity record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical record not found with id : " + id));

        return MedicalRecordResponseDto.builder()
                .id(record.getId())
                .bloodGroup(record.getBloodGroup())
                .diagnosis(record.getDiagnosis())
                .patientName(record.getPatientEntity().getName())   // bi-directional ka kamaal
                .patientAge(record.getPatientEntity().getAge())
                .build();
    }
}
