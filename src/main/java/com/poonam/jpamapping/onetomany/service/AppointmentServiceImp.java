package com.poonam.jpamapping.onetomany.service;

import com.poonam.jpamapping.onetomany.dto.AppointmentRequestDto;
import com.poonam.jpamapping.onetomany.dto.AppointmentResponseDto;
import com.poonam.jpamapping.onetomany.entity.AppointmentEntity;
import com.poonam.jpamapping.onetomany.entity.DoctorEntity;
import com.poonam.jpamapping.onetomany.repository.AppointmentRepository;
import com.poonam.jpamapping.onetomany.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public AppointmentResponseDto createAppointment(Long doctorId, AppointmentRequestDto requestDto) {

// Step 1: Doctor ko DB se nikalo
           DoctorEntity doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        AppointmentEntity appointment = AppointmentEntity.builder()
                .appointmentTime(requestDto.getAppointmentTime())
                .reason(requestDto.getReason())
                .doctor(doctor)
                .build();
        // Step 2: DB me save karo
        AppointmentEntity savedAppointment = appointmentRepository.save(appointment);

        // Step 3: Slip (response) banao
        return AppointmentResponseDto.builder()
                .id(savedAppointment.getId())
                .appointmentTime(savedAppointment.getAppointmentTime())
                .reason(savedAppointment.getReason())
                .build();


    }
}