package com.project.survey.application.port.out.refreshToken;

import com.project.survey.domain.RefreshToken;

public interface CreateRefreshTokenPort {

    RefreshToken createRefreshToken(RefreshToken refreshToken);

}