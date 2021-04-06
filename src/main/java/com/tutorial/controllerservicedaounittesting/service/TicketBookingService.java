package com.tutorial.controllerservicedaounittesting.service;

import com.tutorial.controllerservicedaounittesting.dao.TicketBookingDao;
import com.tutorial.controllerservicedaounittesting.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TicketBookingService {

    @Autowired
    private TicketBookingDao ticketBookingDao;

    public Ticket createTicket(Ticket ticket) {
        return ticketBookingDao.save(ticket);
    }
    public Optional<Ticket> getTicketById(Integer ticketId) {
        return ticketBookingDao.findById(ticketId);
    }
    public Iterable<Ticket> getAllBookedTickets() {
        return ticketBookingDao.findAll();
    }
    public void deleteTicket(Integer ticketId) {
        ticketBookingDao.deleteById(ticketId);
    }
    public Ticket updateTicket(Integer ticketId, String newEmail) {
        Optional<Ticket> ticketFromDb = ticketBookingDao.findById(ticketId);
        if(ticketFromDb.isPresent()) {
            ticketFromDb.get().setEmail(newEmail);
            Ticket updatedTicket = ticketBookingDao.save(ticketFromDb.get());
            return updatedTicket;
        }
        return null;
    }
    public Ticket getTicketByEmail(String email) {
        return ticketBookingDao.findByEmail(email);
    }
}