package com.poonam.jpamapping.manytomany.service;

import com.poonam.jpamapping.manytomany.dto.SpecializationDetailResponseDto;
import com.poonam.jpamapping.manytomany.dto.SpecializationRequestDto;
import com.poonam.jpamapping.manytomany.dto.SpecializationResponseDto;
import com.poonam.jpamapping.manytomany.entity.SpecializationEntity;
import com.poonam.jpamapping.manytomany.repository.SpecializationRepository;
import com.poonam.jpamapping.onetomany.dto.DoctorResponseDto;
import com.poonam.jpamapping.onetomany.entity.DoctorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class SpecializationServiceImpl implements SpecializationService{
    private final SpecializationRepository specializationRepository;
    @Override
    public SpecializationResponseDto createSpecialization(SpecializationRequestDto specializationRequest) {

        // Step 1: Form se Specialization file banao
        SpecializationEntity specialization = SpecializationEntity.builder()
                .name(specializationRequest.getName())
                .build();

        // Step 2: DB me save karo
         SpecializationEntity saved = specializationRepository.save(specialization);

        // Step 3: Slip (response) banao
        return SpecializationResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .build();

    }

    @Override
    public SpecializationDetailResponseDto getSpecializationDetail(Long id) {
        // Step 1: Specialization ki file nikalo
        SpecializationEntity specialization = specializationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialization not found with id : " + id));

        // Step 2: Doctors ki slips ki khali list banao
        List<DoctorResponseDto> doctorDtos = new ArrayList<>();

        // Step 3: Har doctor ke liye slip banao aur list me daalo
        for (DoctorEntity doctor : specialization.getDoctors()) {
            DoctorResponseDto dto = DoctorResponseDto.builder()
                    .id(doctor.getId())
                    .name(doctor.getName())
                    .experience(doctor.getExperience())
                    .build();
            doctorDtos.add(dto);
        }

        // Step 4: Specialization ki slip banao, usme doctors ki list laga do
        return SpecializationDetailResponseDto.builder()
                .id(specialization.getId())
                .name(specialization.getName())
                .doctors(doctorDtos)
                .build();    }
}
