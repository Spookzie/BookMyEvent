package com.spookzie.BookMyEvent.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketTypeResponseDto
{
    private UUID id;
    private String name;
    private Integer totalAvailable;
    private Double price;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}