package com.poonam.jpamapping.onetoone.controller;


import com.poonam.jpamapping.onetoone.dto.MedicalRecordResponseDto;
import com.poonam.jpamapping.onetoone.repository.MedicalRecordRepository;
import com.poonam.jpamapping.onetoone.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medical-record")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @GetMapping("/read/{id}")
public ResponseEntity<MedicalRecordResponseDto> readMedicalRecord(@PathVariable Long id) {
        MedicalRecordResponseDto medicalResponse = medicalRecordService.getMedicalRecordById(id);
        return new ResponseEntity<>(medicalResponse, HttpStatus.OK);
    }
}

