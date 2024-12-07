package com.project.survey.application.port.in.auth;

import com.project.survey.adapter.in.web.dto.response.auth.JwtResponseDto;
import com.project.survey.adapter.in.web.dto.response.auth.MemberResponseDto;
import com.project.survey.application.command.auth.SignInCommand;
import com.project.survey.application.command.auth.SignOutCommand;
import com.project.survey.application.command.auth.SignUpCommand;

/**
 * useCase에서는 Command를 받아서 도메인을 반환한다.
 */
public interface AuthUseCase {
    MemberResponseDto signUp(SignUpCommand signUpCommand);
    JwtResponseDto signInAndCreateJwt(SignInCommand signInCommand);
    Long signOut(SignOutCommand signOutCommand);
}
