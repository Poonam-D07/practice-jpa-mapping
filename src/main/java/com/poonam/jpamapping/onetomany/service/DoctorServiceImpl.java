package com.poonam.jpamapping.onetomany.service;

import com.poonam.jpamapping.onetomany.dto.AppointmentResponseDto;
import com.poonam.jpamapping.onetomany.dto.DoctorDetailResponseDto;
import com.poonam.jpamapping.onetomany.dto.DoctorRequestDto;
import com.poonam.jpamapping.onetomany.dto.DoctorResponseDto;
import com.poonam.jpamapping.onetomany.entity.AppointmentEntity;
import com.poonam.jpamapping.onetomany.entity.DoctorEntity;
import com.poonam.jpamapping.onetomany.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;

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
        // Step 4: Doctor ki slip banao, usme appointments ki list laga do
        return DoctorDetailResponseDto.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .experience(doctor.getExperience())
                .appointments(appointmentDtos)
                .build();
    }
}
