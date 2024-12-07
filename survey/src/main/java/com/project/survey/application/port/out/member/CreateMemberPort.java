package com.project.survey.application.port.out.member;

import com.project.survey.domain.Member;

public interface CreateMemberPort {
    Member createMember(Member member);
}
