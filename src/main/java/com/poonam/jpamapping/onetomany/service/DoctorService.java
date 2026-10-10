package com.poonam.jpamapping.onetomany.service;

import com.poonam.jpamapping.onetomany.dto.DoctorDetailResponseDto;
import com.poonam.jpamapping.onetomany.dto.DoctorRequestDto;
import com.poonam.jpamapping.onetomany.dto.DoctorResponseDto;

public interface DoctorService  {
    DoctorResponseDto createDoctor(DoctorRequestDto requestDto);
    DoctorDetailResponseDto getDoctorById(Long id);
    String addSpecializationToDoctor(Long doctorId, Long specializationId);

}
