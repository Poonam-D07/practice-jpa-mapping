package com.poonam.jpamapping.onetoone.service;

import com.poonam.jpamapping.onetoone.dto.MedicalRecordResponseDto;

public interface MedicalRecordService {
    MedicalRecordResponseDto getMedicalRecordById(Long Id);
}
