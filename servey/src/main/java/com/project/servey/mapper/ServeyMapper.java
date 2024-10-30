package com.project.servey.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyListResponseDto;
import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.adapter.out.persistence.entity.servey.ServeyEntity;
import com.project.servey.application.command.servey.CreateServeyCommand;
import com.project.servey.application.command.servey.UpdateServeyCommand;
import com.project.servey.domain.Servey;
//객체 간 매핑을 자동으로 처리
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ServeyMapper {

    Servey entityToDomain(ServeyEntity serveyEntity);

    ServeyResponseDto domainToResponseDto(Servey servey);

    ServeyEntity domainToEntity(Servey servey);

    Servey commandToDomain(CreateServeyCommand command);
    
    Servey commandToDomain(UpdateServeyCommand command);

    CreateServeyCommand domainToConnCommand(Servey servey);

    List<Servey> entitiesToDomains(List<ServeyEntity> serveyEntities);

    List<ServeyResponseDto> domainsToResponseDtos(List<Servey> serveys);

    List<ServeyListResponseDto> domainsToListResponseDtos(List<Servey> servyes);

    //Servey commandToDomain(UpdateServeyCommand command);

}