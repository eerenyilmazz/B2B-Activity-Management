package com.codevivus.b2b.service;

import com.codevivus.b2b.dtos.TicketDto;
import java.util.List;

public interface TicketService {
    List<TicketDto> getTicketsByUserId(Long userId);
    TicketDto createTicket(TicketDto ticketDto);
}
