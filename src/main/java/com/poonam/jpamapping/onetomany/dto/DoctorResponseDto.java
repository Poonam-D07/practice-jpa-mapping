package com.poonam.jpamapping.onetomany.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseDto {
    private Long id;
    private String name;
    private Integer experience;
}
