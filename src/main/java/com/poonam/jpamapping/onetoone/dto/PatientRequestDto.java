package com.poonam.jpamapping.onetoone.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRequestDto {
    private String name;
    private Integer age;
    private String bloodGroup;
    private String diagnosis;
}

//@Entity mat lagana, DTO ek normal class hai, table nahi banti.