package com.project.servey.application.port.out.servey;

import com.project.servey.domain.Servey;

public interface FindServeyPort {
    Servey findServeyById(Long id);
}
