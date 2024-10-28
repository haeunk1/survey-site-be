package com.project.servey.adapter.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.servey.adapter.in.web.dto.api.ApiResponse;
import com.project.servey.adapter.in.web.dto.request.auth.MemberSignUpRequestDto;
import com.project.servey.adapter.in.web.dto.request.auth.SignInRequestDto;
import com.project.servey.adapter.in.web.dto.request.auth.SignOutRequestDto;
import com.project.servey.adapter.in.web.dto.response.auth.JwtResponseDto;
import com.project.servey.adapter.in.web.dto.response.auth.MemberResponseDto;
import com.project.servey.application.command.auth.SignInCommand;
import com.project.servey.application.command.auth.SignOutCommand;
import com.project.servey.application.command.auth.SignUpCommand;
import com.project.servey.application.port.in.auth.AuthUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    /**
     * @param signUpRequestDTO 회원가입 요청 DTO
     * @return 회원가입 응답 DTO
     * @apiNote 회원가입 요청을 받아 회원가입을 진행합니다.
     */
    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse<MemberResponseDto>> signUpAndPublishEvent(
            @RequestBody MemberSignUpRequestDto signUpRequestDTO
    ) {
        SignUpCommand signUpCommand = SignUpCommand.of(signUpRequestDTO);
        MemberResponseDto responseDTO = authUseCase.signUp(signUpCommand);

        return ResponseEntity.ok(ApiResponse.success(responseDTO));
    }

    /**
     * @param signInRequestDTO 로그인 요청 DTO
     * @return JWT 토큰 응답 DTO
     * @apiNote 로그인 요청을 받아 JWT 토큰을 발급합니다.
     */
    @PostMapping("/signIn")
    public ResponseEntity<ApiResponse<JwtResponseDto>> signInAndCreateJwt(
            @RequestBody SignInRequestDto signInRequestDTO
    ) {
        SignInCommand signInCommand = SignInCommand.of(signInRequestDTO);
        JwtResponseDto responseDTO = authUseCase.signInAndCreateJwt(signInCommand);

        return ResponseEntity.ok(ApiResponse.success(responseDTO));
    }

    /**
     * @param signOutRequestDTO 로그아웃 요청 DTO
     * @return 로그아웃 성공 메시지
     * @apiNote 로그아웃 요청을 받아 JWT 토큰을 삭제합니다.
     */
    @PostMapping("/signOut")
    public ResponseEntity<ApiResponse<Long>> signOutAndDeleteJwt(
            @RequestBody SignOutRequestDto signOutRequestDTO
    ) {
        SignOutCommand signOutCommand = SignOutCommand.of(signOutRequestDTO);
        Long response = authUseCase.signOut(signOutCommand);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
