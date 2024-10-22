package com.project.servey.adapter.out.persistence.adapter;

import com.project.servey.application.port.out.refreshToken.CreateRefreshTokenPort;
import com.project.servey.domain.RefreshToken;
import com.project.servey.util.custom.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

/**
 * 리프레시 토큰 관련 비즈니스 로직을 처리하는 어댑터 클래스
 */
@RequiredArgsConstructor
@PersistenceAdapter
public class RefreshTokenPersistenceAdapter implements CreateRefreshTokenPort{
    
    @Override
    public RefreshToken createRefreshToken(RefreshToken refreshToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRefreshToken'");
    }
    
}
