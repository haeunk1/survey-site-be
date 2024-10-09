package com.project.servey.adapter.out.persistence.adapter;

import com.project.servey.adapter.out.persistence.entity.servey.ServeyEntity;
import com.project.servey.adapter.out.persistence.repository.ServeyRepository;
import com.project.servey.application.port.out.servey.FindServeyPort;
import com.project.servey.domain.Servey;
import com.project.servey.exception.ErrorCode;
import com.project.servey.exception.ServeyException;
import com.project.servey.mapper.ServeyMapper;
import com.project.servey.util.custom.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class ServeyPersistenceAdapter implements FindServeyPort{
    private final ServeyRepository serveyRepository;
    private final ServeyMapper serveyMapper;
    @Override
    public Servey findServeyById(Long id) {
        ServeyEntity serveyEntity = serveyRepository.findById(id)
                    .orElseThrow(() -> new ServeyException(ErrorCode.SERVEY_NOT_FOUND));
        return serveyMapper.entityToDomain(serveyEntity);
    }
}
