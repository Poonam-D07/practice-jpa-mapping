package com.poonam.jpamapping.onetomany.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {
    private String name;
    private Integer experience;
}
