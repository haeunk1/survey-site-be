package com.project.servey.application.service.servey;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyListResponseDto;
import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.application.command.servey.FindServeyCommand;
import com.project.servey.application.command.servey.FindServeyListCommand;
import com.project.servey.application.port.in.servey.FindServeyUseCase;
import com.project.servey.application.port.out.servey.FindServeyPort;
import com.project.servey.domain.Servey;
import com.project.servey.exception.ErrorCode;
import com.project.servey.exception.ServeyException;
import com.project.servey.mapper.ServeyMapper;
import com.project.servey.util.custom.UseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@UseCase
public class FindServeyService implements FindServeyUseCase {
    private final FindServeyPort findServeyPort;
    private final ServeyMapper serveyMapper;

    @Override
    public ServeyResponseDto findServey(FindServeyCommand findCommand) {
        checkIsServeyExist(findCommand.getServeyId());
        Servey findServey = findServeyPort.findServeyById(findCommand.getServeyId());
        return serveyMapper.domainToResponseDto(findServey);
    }

    @Override
    public List<ServeyResponseDto> findServeyAllList() {
        List<Servey> list = findServeyPort.findServeyAllList();
        return serveyMapper.domainsToResponseDtos(list);
    }

    /**
     * @param findServeyListCommand 리스트 필터링 도메인
     * @return 필터링된 설문조사 리스트
     * @apiNote 필터링된 설문조사 리스트 return
     */
    @Override
    public List<ServeyListResponseDto> findServeyFilteredList(FindServeyListCommand findServeyListCommand) {
        List<ServeyListResponseDto> list = findServeyPort.findServeyFilteredList(findServeyListCommand);
        return list;
    }

    /**
     * [READ] 삭제된 게시글이 아닌지 검증
     * serveyId로 게시글이 존재하는지 검증하는 내부 메서드
     */
    public boolean checkIsServeyExist(Long serveyId) {
        boolean isCommentExist = findServeyPort.checkIsServeyExist(serveyId);
        if(!isCommentExist) {
            throw new ServeyException(ErrorCode.SERVEY_NOT_FOUND);
        }
        return true;
    }
}
