package com.poonam.jpamapping.onetomany.service;

import com.poonam.jpamapping.manytomany.dto.SpecializationResponseDto;
import com.poonam.jpamapping.manytomany.entity.SpecializationEntity;
import com.poonam.jpamapping.manytomany.repository.SpecializationRepository;
import com.poonam.jpamapping.onetomany.dto.AppointmentResponseDto;
import com.poonam.jpamapping.onetomany.dto.DoctorDetailResponseDto;
import com.poonam.jpamapping.onetomany.dto.DoctorRequestDto;
import com.poonam.jpamapping.onetomany.dto.DoctorResponseDto;
import com.poonam.jpamapping.onetomany.entity.AppointmentEntity;
import com.poonam.jpamapping.onetomany.entity.DoctorEntity;
import com.poonam.jpamapping.onetomany.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;
    private final SpecializationRepository specializationRepository;


    @Override
    public DoctorResponseDto createDoctor(DoctorRequestDto requestDto) {

        // Step 1: Form se Doctor file banao
         DoctorEntity doctor = DoctorEntity.builder()
                .name(requestDto.getName())
                .experience(requestDto.getExperience())
                .build();

        // Step 2: DB me save karo
        DoctorEntity saveDoctor = doctorRepository.save(doctor);

        // Step 3: Slip (response) banao
        return DoctorResponseDto.builder()
                .id(saveDoctor.getId())
                .name(saveDoctor.getName())
                .experience(saveDoctor.getExperience())
                .build();
    }

    @Transactional
    @Override
    public DoctorDetailResponseDto getDoctorById(Long id) {
        DoctorEntity doctor = doctorRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Doctor Not Found with id : " + id));

        // Step 2: Appointments ki slips (DTO) ki khali list banao
        List<AppointmentResponseDto> appointmentDtos = new ArrayList<>();

        // Step 3: Doctor ke har appointment ke liye ek slip banao aur list me daalo
        for(AppointmentEntity appointment : doctor.getAppointments()){
            AppointmentResponseDto dto = AppointmentResponseDto.builder()
                    .id(appointment.getId())
                    .appointmentTime(appointment.getAppointmentTime())
                    .reason(appointment.getReason())
                    .build();

            appointmentDtos.add(dto);
        }

        // NAYA (1): Specializations ki slips ki khali list
        List<SpecializationResponseDto> specializationDtos = new ArrayList<>();

        // NAYA (2): Doctor ki har specialization ke liye slip banao aur list me daalo
         for(SpecializationEntity specialization : doctor.getSpecializations()){
             SpecializationResponseDto dto = SpecializationResponseDto.builder()
                     .id(specialization.getId())
                     .name(specialization.getName())
                     .build();
             specializationDtos.add(dto);
         }

        // Step 4: Doctor ki slip banao, usme appointments ki list laga do
        return DoctorDetailResponseDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .experience(doctor.getExperience())
                .appointments(appointmentDtos)
                .specializations(specializationDtos)
                .build();
    }

    @Override
    @Transactional
    public String addSpecializationToDoctor(Long doctorId, Long specializationId) {
        // Step 1: Doctor ki file nikalo
        DoctorEntity doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()->new RuntimeException("Doctor not found with id : " + doctorId));

        // Step 2: Specialization ki file nikalo
        SpecializationEntity specialization = specializationRepository.findById (specializationId)
                .orElseThrow(()-> new RuntimeException("Specialization not found with id : " + specializationId));

        // Step 3: Doctor ke set me Specialization jodo
        doctor.getSpecializations().add(specialization);

        // Step 4: Save karo
        doctorRepository.save(doctor);

        return "Specialization added to doctor successfully";
    }
}
