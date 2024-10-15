package com.project.servey.application.port.in.servey;

import com.project.servey.application.command.servey.UpdateServeyCommand;

public interface UpdateServeyUseCase {
    Long updateServey(UpdateServeyCommand command);
}
