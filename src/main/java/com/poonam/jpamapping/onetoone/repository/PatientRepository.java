package com.poonam.jpamapping.onetoone.repository;

import com.poonam.jpamapping.onetoone.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
}
