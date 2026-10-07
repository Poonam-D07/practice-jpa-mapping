package com.poonam.jpamapping.onetoone.controller;

import com.poonam.jpamapping.onetoone.dto.PatientRequestDto;
import com.poonam.jpamapping.onetoone.dto.PatientResponseDto;
import com.poonam.jpamapping.onetoone.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<PatientResponseDto> createPatient(@RequestBody PatientRequestDto requestDto) {
        PatientResponseDto response = patientService.createPatient(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
