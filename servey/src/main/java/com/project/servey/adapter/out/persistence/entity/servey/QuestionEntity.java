package com.project.servey.adapter.out.persistence.entity.servey;

import com.project.servey.adapter.out.persistence.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="Question")
public class QuestionEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id") 
    private Long questionId;          // PK

    @Column(name = "servey_id", nullable = false)
    private Long serveyId;
    
    @Column(name = "question", nullable = false)
    private String question;    
    
}
