package com.project.servey.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.project.servey.adapter.out.persistence.entity.member.RefreshTokenEntity;
import com.project.servey.domain.RefreshToken;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {MemberMapper.class} // memberMapper를 사용합니다.
)
public interface RefreshTokenMapper {

    @Mapping(target = "memberEntity", source = "member")
    RefreshTokenEntity domainToEntity(RefreshToken refreshToken);

    @Mapping(target = "member", source = "memberEntity")
    RefreshToken entityToDomain(RefreshTokenEntity refreshTokenEntity);

}