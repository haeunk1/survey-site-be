package com.project.servey.adapter.out.persistence.adapter;

import com.project.servey.adapter.out.persistence.entity.member.MemberEntity;
import org.springframework.util.ObjectUtils;

import com.project.servey.adapter.out.persistence.entity.member.RefreshTokenEntity;
import com.project.servey.adapter.out.persistence.repository.RefreshTokenRepository;
import com.project.servey.application.port.out.refreshToken.CreateRefreshTokenPort;
import com.project.servey.application.port.out.refreshToken.DeleteRefreshTokenPort;
import com.project.servey.domain.Member;
import com.project.servey.domain.RefreshToken;
import com.project.servey.exception.ErrorCode;
import com.project.servey.exception.ServeyException;
import com.project.servey.mapper.MemberMapper;
import com.project.servey.mapper.RefreshTokenMapper;
import com.project.servey.util.custom.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

/**
 * 리프레시 토큰 관련 비즈니스 로직을 처리하는 어댑터 클래스
 */
@RequiredArgsConstructor
@PersistenceAdapter
public class RefreshTokenPersistenceAdapter implements CreateRefreshTokenPort, DeleteRefreshTokenPort{
    
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberMapper memberMapper;
    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public RefreshToken createRefreshToken(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenMapper.domainToEntity(refreshToken);
        
        validRefreshTokenEntity(refreshTokenEntity);

        // RefreshToken 생성 및 저장
        RefreshTokenEntity savedRefreshTokenEntity = refreshTokenRepository.save(refreshTokenEntity);
        return refreshTokenMapper.entityToDomain(savedRefreshTokenEntity);
    }
    
    /**
     * @param refreshTokenEntity RefreshTokenEntity
     * @apiNote RefreshTokenEntity 유효성 검사
     */
    private void validRefreshTokenEntity(RefreshTokenEntity refreshTokenEntity) {
        if (ObjectUtils.isEmpty(refreshTokenEntity)) throw new ServeyException(ErrorCode.DATA_NOT_FOUND);
    }

    /**
     * @param memberEntity MemberEntity
     * @apiNote MemberEntity 유효성 검사
     */
    private void validMemberEntity(MemberEntity memberEntity) {
        if (ObjectUtils.isEmpty(memberEntity)) throw new ServeyException(ErrorCode.DATA_NOT_FOUND);
    }

    /**
     * @param member 회원
     * @return 삭제 여부
     * @apiNote RefreshToken 삭제
     */
    @Override
    public Boolean deleteRefreshToken(Member member) {
        MemberEntity memberEntity = memberMapper.domainToEntity(member);
        validMemberEntity(memberEntity);
        // 회원의 RefreshToken 삭제
        refreshTokenRepository.deleteByMemberEntity(memberEntity);
        return true;
    }
    
}
