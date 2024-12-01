package com.project.servey.adapter.out.persistence.entity.point;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.project.servey.adapter.out.persistence.entity.constant.PointTransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="point_transaction")
public class PointTransactionEntity {
    @Id
    @Column(name = "transaction_id") 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;          

    @Column(name = "type", nullable = false)
    private PointTransactionType type;      

    @Column(name = "amount", nullable = false)
    private int amount;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "description", nullable = false)
    private String description;
}
