package com.tutorial.controllerservicedaounittesting.controller;

import com.tutorial.controllerservicedaounittesting.entities.Ticket;
import com.tutorial.controllerservicedaounittesting.service.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="/api/tickets")
public class TicketBookingController {

    @Autowired
    private TicketBookingService ticketBookingService;


    @PostMapping(value="/create",consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public Ticket createTicket(@RequestBody Ticket ticket){
        return ticketBookingService.createTicket(ticket);
    }

    @GetMapping(value="/ticketId/{ticketId}",produces=MediaType.APPLICATION_JSON_VALUE)
    public Optional<Ticket> getTicketById(@PathVariable("ticketId")Integer ticketId){
        return ticketBookingService.getTicketById(ticketId);
    }


    @GetMapping(value="/alltickets",produces=MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Ticket> getAllBookedTickets(){
        return ticketBookingService.getAllBookedTickets();
    }


    @GetMapping(value="/email/{email:.+}",produces=MediaType.APPLICATION_JSON_VALUE)
    public Ticket getTicketByEmail(@PathVariable("email")String email){
        return ticketBookingService.getTicketByEmail(email);
    }


    @DeleteMapping(value="/ticketId/{ticketId}")
    public void deleteTicket(@PathVariable("ticketId")Integer ticketId){
        ticketBookingService.deleteTicket(ticketId);
    }


    @PutMapping(value="/ticketId/{ticketId}/email/{newEmail:.+}",produces=MediaType.APPLICATION_JSON_VALUE)
    public Ticket updateTicket(@PathVariable("ticketId")Integer ticketId,@PathVariable("newEmail")String newEmail){
        return ticketBookingService.updateTicket(ticketId,newEmail);
    }
}