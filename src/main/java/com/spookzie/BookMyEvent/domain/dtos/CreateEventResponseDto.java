package com.spookzie.BookMyEvent.domain.dtos;

import com.spookzie.BookMyEvent.domain.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventResponseDto
{
    private UUID id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private EventStatus status;
    private List<CreateTicketTypeResponseDto> ticketTypes;
}