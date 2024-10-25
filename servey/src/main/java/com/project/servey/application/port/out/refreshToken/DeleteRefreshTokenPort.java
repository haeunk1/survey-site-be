package com.project.servey.application.port.out.refreshToken;

import com.project.servey.domain.Member;

public interface DeleteRefreshTokenPort {
    Boolean deleteRefreshToken(Member member);
}
