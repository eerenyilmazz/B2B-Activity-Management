package com.codevivus.b2b.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean canManageEvents;
    private boolean canInitiateB2B;
}