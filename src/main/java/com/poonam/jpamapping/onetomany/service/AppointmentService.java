package com.poonam.jpamapping.onetomany.service;

import com.poonam.jpamapping.onetomany.dto.AppointmentRequestDto;
import com.poonam.jpamapping.onetomany.dto.AppointmentResponseDto;

public interface AppointmentService {
    AppointmentResponseDto createAppointment(Long doctorId, AppointmentRequestDto requestDto);
}
