package com.project.servey.application.port.out.member;

import com.project.servey.domain.Member;

public interface FindMemberPort {

    Member findMemberById(Long memberId);

    Member findMember(Member member);

    Member findMemberByEmail(String email);

}