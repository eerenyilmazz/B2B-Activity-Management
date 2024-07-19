package com.codevivus.b2b.controller;

import com.codevivus.b2b.dtos.TicketDto;
import com.codevivus.b2b.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketDto>> getUserTickets(@PathVariable Long userId) throws IOException {
        List<TicketDto> tickets = ticketService.getTicketsByUserId(userId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) throws IOException {
        TicketDto createdTicket = ticketService.createTicket(ticketDto);
        return ResponseEntity.ok(createdTicket);
    }
}