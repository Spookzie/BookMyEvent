package com.spookzie.BookMyEvent.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketTypeRequest
{
    private String name;
    private Double price;
    private String description;
    private Integer totalAvailable;
}