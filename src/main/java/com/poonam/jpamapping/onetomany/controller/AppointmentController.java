package com.poonam.jpamapping.onetomany.controller;

import com.poonam.jpamapping.onetomany.dto.AppointmentRequestDto;
import com.poonam.jpamapping.onetomany.dto.AppointmentResponseDto;
import com.poonam.jpamapping.onetomany.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/create/{doctorId}")
    public ResponseEntity<AppointmentResponseDto> createAppointment(@PathVariable Long doctorId, @RequestBody AppointmentRequestDto requestDto){

        AppointmentResponseDto response = appointmentService.createAppointment(doctorId, requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
