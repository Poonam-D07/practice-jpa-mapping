package com.poonam.jpamapping.onetomany.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDto {
    private LocalDateTime appointmentTime;
    private String reason;
}
