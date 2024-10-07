package com.project.servey.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.adapter.out.persistence.entity.servey.ServeyEntity;
import com.project.servey.application.command.servey.CreateServeyCommand;
import com.project.servey.domain.Servey;
//객체 간 매핑을 자동으로 처리
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ServeyMapper {

    Servey entityToDomain(ServeyEntity postEntity);

    ServeyResponseDto domainToResponseDto(Servey post);

    ServeyEntity domainToEntity(Servey post);

    Servey commandToDomain(CreateServeyCommand command);

    //Servey commandToDomain(UpdateServeyCommand command);

}