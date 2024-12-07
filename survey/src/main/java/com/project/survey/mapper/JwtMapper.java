package com.project.survey.mapper;

import org.mapstruct.Mapper;

import com.project.survey.adapter.in.web.dto.response.auth.JwtResponseDto;
import com.project.survey.domain.Jwt;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JwtMapper {

    JwtResponseDto domainToResponseDTO(Jwt jwt);

}