package com.poonam.jpamapping.onetoone.service;

import com.poonam.jpamapping.onetoone.dto.PatientRequestDto;
import com.poonam.jpamapping.onetoone.dto.PatientResponseDto;
import org.springframework.stereotype.Service;


public interface PatientService {
    PatientResponseDto createPatient(PatientRequestDto  requestDto);
}
