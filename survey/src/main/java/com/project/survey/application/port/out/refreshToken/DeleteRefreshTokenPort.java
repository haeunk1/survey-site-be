package com.project.survey.application.port.out.refreshToken;

import com.project.survey.domain.Member;

public interface DeleteRefreshTokenPort {
    Boolean deleteRefreshToken(Member member);
}
