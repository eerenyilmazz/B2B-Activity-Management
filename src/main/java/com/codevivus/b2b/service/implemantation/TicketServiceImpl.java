package com.codevivus.b2b.service.implementation;

import com.codevivus.b2b.dtos.TicketDto;
import com.codevivus.b2b.entity.Activity;
import com.codevivus.b2b.entity.Ticket;
import com.codevivus.b2b.entity.User;
import com.codevivus.b2b.repository.ActivityRepository;
import com.codevivus.b2b.repository.TicketRepository;
import com.codevivus.b2b.repository.UserRepository;
import com.codevivus.b2b.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    @Override
    public List<TicketDto> getTicketsByUserId(Long userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        return tickets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket ticket = convertToEntity(ticketDto);
        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToDto(savedTicket);
    }

    private TicketDto convertToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setActivityId(ticket.getActivity().getId());
        ticketDto.setUserId(ticket.getUser().getId());
        return ticketDto;
    }

    private Ticket convertToEntity(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        Activity activity = activityRepository.findById(ticketDto.getActivityId())
                .orElseThrow(() -> new RuntimeException("Activity not found"));
        User user = userRepository.findById(ticketDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        ticket.setActivity(activity);
        ticket.setUser(user);
        return ticket;
    }
}
