package com.poonam.jpamapping.manytomany.controller;

import com.poonam.jpamapping.manytomany.dto.SpecializationDetailResponseDto;
import com.poonam.jpamapping.manytomany.dto.SpecializationRequestDto;
import com.poonam.jpamapping.manytomany.dto.SpecializationResponseDto;
import com.poonam.jpamapping.manytomany.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/specializations")
@RequiredArgsConstructor
public class SpecializationController {

    private final SpecializationService specializationService;

    @PostMapping("/create")
    public ResponseEntity<SpecializationResponseDto> createSpecialization(@RequestBody SpecializationRequestDto requestDto){
        SpecializationResponseDto response = specializationService.createSpecialization(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public  ResponseEntity<SpecializationDetailResponseDto> readSpecialization(@PathVariable Long id){
        SpecializationDetailResponseDto response = specializationService.getSpecializationDetail(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
