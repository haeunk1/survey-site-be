package com.project.servey.application.port.in.servey;

import java.util.List;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyListResponseDto;
import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;
import com.project.servey.application.command.servey.FindServeyCommand;
import com.project.servey.application.command.servey.FindServeyListCommand;

public interface FindServeyUseCase {
    ServeyResponseDto findServey(FindServeyCommand findCommand);
    List<ServeyResponseDto> findServeyAllList();
    List<ServeyListResponseDto> findServeyFilteredList(FindServeyListCommand findServeyListCommand);
}
