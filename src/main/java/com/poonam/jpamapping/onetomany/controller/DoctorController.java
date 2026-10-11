package com.poonam.jpamapping.onetomany.controller;

import com.poonam.jpamapping.common.PageResponseDto;
import com.poonam.jpamapping.onetomany.dto.DoctorDetailResponseDto;
import com.poonam.jpamapping.onetomany.dto.DoctorRequestDto;
import com.poonam.jpamapping.onetomany.dto.DoctorResponseDto;
import com.poonam.jpamapping.onetomany.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/create")
    public ResponseEntity<DoctorResponseDto> createDoctor(@Valid @RequestBody DoctorRequestDto requestDto) {
        DoctorResponseDto response = doctorService.createDoctor(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<DoctorDetailResponseDto> readDoctor(@PathVariable Long id) {
        DoctorDetailResponseDto response = doctorService.getDoctorById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{doctorId}/specializations/{specializationId}")
    public ResponseEntity<String> addSpecialization(@PathVariable Long doctorId, @PathVariable Long specializationId) {
        String response = doctorService.addSpecializationToDoctor(doctorId, specializationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/readAll")
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors() {
        List<DoctorResponseDto> response = doctorService.getAllDoctors();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/paged")
    public ResponseEntity<PageResponseDto<DoctorResponseDto>> getDoctorPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
             @RequestParam(defaultValue = "id")String sortBy,
            @RequestParam(defaultValue = "asc") String direction){

        PageResponseDto<DoctorResponseDto> response = doctorService.getAllDoctorsPaged(page, size, sortBy, direction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //==================
    //SEARCH API
    //================
    @GetMapping("/search")
    public ResponseEntity<PageResponseDto<DoctorResponseDto>> searchDoctors(
            @RequestParam String name,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "5") int size){
        PageResponseDto<DoctorResponseDto> response = doctorService.searchDoctorsByName(name, page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
