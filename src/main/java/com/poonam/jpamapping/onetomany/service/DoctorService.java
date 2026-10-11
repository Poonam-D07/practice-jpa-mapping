package com.poonam.jpamapping.onetomany.service;

import com.poonam.jpamapping.common.PageResponseDto;
import com.poonam.jpamapping.onetomany.dto.DoctorDetailResponseDto;
import com.poonam.jpamapping.onetomany.dto.DoctorRequestDto;
import com.poonam.jpamapping.onetomany.dto.DoctorResponseDto;

import java.util.List;

public interface DoctorService  {
    DoctorResponseDto createDoctor(DoctorRequestDto requestDto);
    DoctorDetailResponseDto getDoctorById(Long id);
    String addSpecializationToDoctor(Long doctorId, Long specializationId);

    List<DoctorResponseDto> getAllDoctors();

    PageResponseDto<DoctorResponseDto> getAllDoctorsPaged(int page, int size, String sortBy, String direction);

    PageResponseDto<DoctorResponseDto> searchDoctorsByName(String name, int page, int size);
}
