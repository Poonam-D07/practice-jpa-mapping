package com.poonam.jpamapping.onetoone.repository;

import com.poonam.jpamapping.onetoone.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity,Long> {

}
