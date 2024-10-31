package com.project.servey.application.port.out.servey;

import java.util.List;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyListResponseDto;
import com.project.servey.application.command.servey.FindServeyListCommand;
import com.project.servey.domain.Servey;

public interface FindServeyPort {
    Servey findServeyById(Long id);
    List<Servey> findServeyAllList();
    List<ServeyListResponseDto> findServeyFilteredList(FindServeyListCommand command);
}
