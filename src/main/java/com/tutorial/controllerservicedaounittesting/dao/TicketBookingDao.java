package com.tutorial.controllerservicedaounittesting.dao;

import com.tutorial.controllerservicedaounittesting.entities.Ticket;

import org.springframework.data.repository.CrudRepository;



public interface TicketBookingDao extends CrudRepository<Ticket, Integer> {
    Ticket findByEmail(String email);
}
