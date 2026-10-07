package com.poonam.jpamapping.onetoone.controller;

import com.poonam.jpamapping.onetoone.dto.PatientRequestDto;
import com.poonam.jpamapping.onetoone.dto.PatientResponseDto;
import com.poonam.jpamapping.onetoone.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    //====================================
    //        CREATE PATIENT
    //====================================

    @PostMapping("/create")
    public ResponseEntity<PatientResponseDto> createPatient(@RequestBody PatientRequestDto requestDto) {
        PatientResponseDto response = patientService.createPatient(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping("/readAll")
//    public  ResponseEntity<List<PatientResponseDto>> readAllPatients() {
//        return null;
//    }

    //====================================
    //        READ PATIENT BY ID
    //====================================

    @GetMapping("/read/{id}")
    public ResponseEntity<PatientResponseDto> readPatient(@PathVariable Long id) {
        PatientResponseDto response = patientService.getPatientById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
