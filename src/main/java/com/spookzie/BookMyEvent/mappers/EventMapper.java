package com.spookzie.BookMyEvent.mappers;

import com.spookzie.BookMyEvent.domain.CreateEventRequest;
import com.spookzie.BookMyEvent.domain.CreateTicketTypeRequest;
import com.spookzie.BookMyEvent.domain.dtos.CreateEventRequestDto;
import com.spookzie.BookMyEvent.domain.dtos.CreateEventResponseDto;
import com.spookzie.BookMyEvent.domain.dtos.CreateTicketTypeRequestDto;
import com.spookzie.BookMyEvent.domain.entities.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper
{
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);
}