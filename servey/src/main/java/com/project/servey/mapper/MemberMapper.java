package com.project.servey.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.project.servey.adapter.in.web.dto.response.auth.MemberResponseDto;
import com.project.servey.adapter.out.persistence.entity.member.MemberEntity;
import com.project.servey.application.command.auth.SignInCommand;
import com.project.servey.application.command.auth.SignOutCommand;
import com.project.servey.application.command.auth.SignUpCommand;
import com.project.servey.domain.Member;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MemberMapper {
    // 응답 도메인을 회원가입 응답 DTO로 변환
    MemberResponseDto domainToResponseDTO(Member member);

    // 도메인을 엔티티로 변환
    MemberEntity domainToEntity(Member member);

    // 로그아웃 요청 DTO를 엔티티로 변환
    //Member toDomain(SignOutRequestDto logoutRequest);

    // MemberEntity 엔티티를 DTO로 변환
    Member toDomain(MemberEntity memberEntity);

    // 로그인 요청 Command를 도메인으로 변환
    Member commandToDomain(SignInCommand signInCommand);

    // 회원가입 요청 Command를 도메인으로 변환
    Member commandToDomain(SignUpCommand signUpCommand);

    // 로그아웃 요청 Command를 도메인으로 변환
    Member commandToDomain(SignOutCommand signOutCommand);

    // 회원 정보 수정 Command를 도메인으로 변환
    //Member commandToDomain(UpdateMemberCommand command);

    // 도메인을 응답 DTO로 변환
    MemberResponseDto domainToResponse(Member updatedMember);


}
