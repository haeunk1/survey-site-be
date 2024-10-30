package com.project.servey.adapter.out.persistence.entity.servey;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="Answer")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id") 
    private Long answerId;          // PK

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;
    
    @Column(name = "answer", nullable = false)
    private String answer;  

    @CreatedDate
    @CreationTimestamp
    @Column(name = "submission_date", updatable = false)
    private LocalDateTime submissionDate;
}
