package com.poonam.jpamapping.manytomany.repository;

import com.poonam.jpamapping.manytomany.entity.SpecializationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<SpecializationEntity, Long> {
}
