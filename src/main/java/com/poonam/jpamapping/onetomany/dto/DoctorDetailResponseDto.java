package com.poonam.jpamapping.onetomany.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDetailResponseDto {
    private Long id;
    private String name;
    private Integer experience;
    private List<AppointmentResponseDto> appointments;
}
