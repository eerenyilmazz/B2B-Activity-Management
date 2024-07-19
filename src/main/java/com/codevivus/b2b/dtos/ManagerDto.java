package com.codevivus.b2b.dtos;

import com.codevivus.b2b.entity.User;
import lombok.Data;

@Data
public class ManagerDto {
    private Long id;
    private User user;
    private boolean canManageEvents;
    private boolean canInitiateB2B;
}
