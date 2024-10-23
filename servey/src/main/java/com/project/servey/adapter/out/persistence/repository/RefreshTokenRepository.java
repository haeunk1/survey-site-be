package com.project.servey.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.servey.adapter.out.persistence.entity.member.MemberEntity;
import com.project.servey.adapter.out.persistence.entity.member.RefreshTokenEntity;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByToken(String token);

    Optional<RefreshTokenEntity> findRefreshTokenEntityByToken(String refreshToken);

    void deleteByMemberEntity(MemberEntity memberEntity);

}