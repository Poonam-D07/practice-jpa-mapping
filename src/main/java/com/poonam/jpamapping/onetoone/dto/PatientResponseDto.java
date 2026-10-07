package com.poonam.jpamapping.onetoone.dto;

import lombok.*;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PatientResponseDto {

    private Long id;
    private String name;
    private Integer age;
    private String bloodGroup;
    private String diagnosis;
}
