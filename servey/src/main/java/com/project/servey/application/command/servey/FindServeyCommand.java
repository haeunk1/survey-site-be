package com.project.servey.application.command.servey;

import com.project.servey.exception.ErrorCode;

import com.project.servey.exception.ServeyException;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class FindServeyCommand {
    private Long serveyId;

    // factory method
    public static FindServeyCommand of(Long serveyId) {
        if (serveyId == null) {
            throw new ServeyException(ErrorCode.SERVEY_ID_IS_NULL);
        }

        return FindServeyCommand.builder()
                .serveyId(serveyId)
                .build();
    }
}
