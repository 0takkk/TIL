package com.example.cleanarchitecture.account.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "activity")
@NoArgsConstructor
@AllArgsConstructor
public class ActivityJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    private Long ownerAccountId;

    private Long sourceAccountId;

    private Long targetAccountId;

    private Long amount;
}
