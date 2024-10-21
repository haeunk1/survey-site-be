package com.project.servey.adapter.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.servey.adapter.in.web.dto.api.ApiResponse;
import com.project.servey.adapter.in.web.dto.request.auth.MemberSignUpRequestDto;
import com.project.servey.adapter.in.web.dto.response.auth.MemberResponseDto;
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
}
