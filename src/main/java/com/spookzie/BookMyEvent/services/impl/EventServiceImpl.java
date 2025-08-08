package com.spookzie.BookMyEvent.services.impl;

import com.spookzie.BookMyEvent.domain.CreateEventRequest;
import com.spookzie.BookMyEvent.domain.entities.Event;
import com.spookzie.BookMyEvent.domain.entities.TicketType;
import com.spookzie.BookMyEvent.domain.entities.User;
import com.spookzie.BookMyEvent.exceptions.UserNotFoundException;
import com.spookzie.BookMyEvent.repositories.EventRepository;
import com.spookzie.BookMyEvent.repositories.UserRepository;
import com.spookzie.BookMyEvent.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService
{
    private final UserRepository userRepository;
    private final EventRepository eventRepository;


    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest eventRequest)
    {
        User organizer = this.userRepository.findById(organizerId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with ID '%s' not found", organizerId))
                );

        List<TicketType> ticketTypesToCreate = eventRequest.getTicketTypes()
                .stream()
                .map(
                        ticketType -> {
                            TicketType ticketTypeToCreate = new TicketType();
                            ticketTypeToCreate.setName(ticketType.getName());
                            ticketTypeToCreate.setPrice(ticketType.getPrice());
                            ticketTypeToCreate.setDescription(ticketType.getDescription());
                            ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());

                            return ticketTypeToCreate;
                        }
                ).toList();

        Event eventToCreate = createEvent(eventRequest, organizer, ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }

    // Helper method
    private Event createEvent(CreateEventRequest eventRequest, User organizer, List<TicketType> ticketTypesToCreate)
    {
        Event eventToCreate = new Event();

        eventToCreate.setName(eventRequest.getName());
        eventToCreate.setStart(eventRequest.getStart());
        eventToCreate.setEnd(eventRequest.getEnd());
        eventToCreate.setVenue(eventRequest.getVenue());
        eventToCreate.setSalesStart(eventRequest.getSalesStart());
        eventToCreate.setSalesEnd(eventRequest.getSalesEnd());
        eventToCreate.setStatus(eventRequest.getStatus());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventToCreate;
    }
}
