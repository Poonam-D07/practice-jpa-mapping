package com.poonam.jpamapping.manytomany.dto;

import com.poonam.jpamapping.onetomany.dto.DoctorResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecializationDetailResponseDto {
    private Long id;
    private String name;
    private List<DoctorResponseDto> doctors;
}
