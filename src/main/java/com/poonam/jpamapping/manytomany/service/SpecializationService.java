package com.poonam.jpamapping.manytomany.service;

import com.poonam.jpamapping.manytomany.dto.SpecializationDetailResponseDto;
import com.poonam.jpamapping.manytomany.dto.SpecializationRequestDto;
import com.poonam.jpamapping.manytomany.dto.SpecializationResponseDto;

public interface SpecializationService {
     SpecializationResponseDto createSpecialization(SpecializationRequestDto specializationRequest);

     SpecializationDetailResponseDto getSpecializationDetail(Long id);
}
