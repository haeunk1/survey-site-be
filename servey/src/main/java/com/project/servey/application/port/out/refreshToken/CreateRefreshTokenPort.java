package com.project.servey.application.port.out.refreshToken;

import com.project.servey.domain.RefreshToken;

public interface CreateRefreshTokenPort {

    RefreshToken createRefreshToken(RefreshToken refreshToken);

}