package com.project.survey.application.port.out.member;

import com.project.survey.domain.Member;

public interface FindMemberPort {

    Member findMemberById(Long memberId);

    Member findMemberByEmail(String email);

}