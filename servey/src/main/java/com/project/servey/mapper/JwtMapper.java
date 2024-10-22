package com.project.servey.mapper;

import org.mapstruct.Mapper;

import com.project.servey.adapter.in.web.dto.response.auth.JwtResponseDto;
import com.project.servey.domain.Jwt;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JwtMapper {

    JwtResponseDto domainToResponseDTO(Jwt jwt);

}