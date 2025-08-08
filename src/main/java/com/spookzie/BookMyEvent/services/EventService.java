package com.spookzie.BookMyEvent.services;

import com.spookzie.BookMyEvent.domain.CreateEventRequest;
import com.spookzie.BookMyEvent.domain.entities.Event;

import java.util.UUID;


public interface EventService
{
    Event createEvent(UUID organizerId, CreateEventRequest eventRequest);
}