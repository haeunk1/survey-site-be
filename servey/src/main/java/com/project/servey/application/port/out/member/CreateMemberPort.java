package com.project.servey.application.port.out.member;

import com.project.servey.domain.Member;

public interface CreateMemberPort {
    Member createMember(Member member);
}
