package com.poonam.jpamapping.onetomany.controller;

import com.poonam.jpamapping.onetomany.dto.DoctorDetailResponseDto;
import com.poonam.jpamapping.onetomany.dto.DoctorRequestDto;
import com.poonam.jpamapping.onetomany.dto.DoctorResponseDto;
import com.poonam.jpamapping.onetomany.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/create")
    public ResponseEntity <DoctorResponseDto> createDoctor(@RequestBody DoctorRequestDto requestDto){
        DoctorResponseDto response = doctorService.createDoctor(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<DoctorDetailResponseDto> readDoctor(@PathVariable Long id) {
        DoctorDetailResponseDto response = doctorService.getDoctorById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
