package com.project.servey.adapter.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.servey.adapter.out.persistence.entity.servey.ServeyEntity;

public interface ServeyRepository extends JpaRepository<ServeyEntity,Long>{
    Optional<ServeyEntity> findByServeyIdAndDeleteYn(Long serveyId, String deleteYn);
}
