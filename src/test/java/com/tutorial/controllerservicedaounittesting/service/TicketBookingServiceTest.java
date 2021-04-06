package com.tutorial.controllerservicedaounittesting.service;

import com.tutorial.controllerservicedaounittesting.dao.TicketBookingDao;
import com.tutorial.controllerservicedaounittesting.entities.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.optional;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketBookingServiceTest {

    @Autowired
    private TicketBookingService ticketBookingService;

    @MockBean
    private TicketBookingDao ticketBookingDao;


    //testing create
    @Test
    public void testCreateTicket(){
        Ticket ticket = new Ticket();
        ticket.setPassengerName("Kassahun Assfaw");
        ticket.setSourceStation("Addis Ababa");
        ticket.setDestStation("Atlanta");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kassamsay@gmail.com");

        Mockito.when(ticketBookingDao.save(ticket)).thenReturn(ticket);
        assertThat(ticketBookingService.createTicket(ticket)).isEqualTo(ticket);

    }
     //testing get ticket by Id

    @Test
    public void testingGetTicketById(){
        Ticket ticket = new Ticket();
        ticket.setTicketId(1);
        ticket.setPassengerName("Kassahun Assfaw");
        ticket.setSourceStation("Addis Ababa");
        ticket.setDestStation("Atlanta");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kassamsay@gmail.com");

        Mockito.when(ticketBookingDao.findById(1)).thenReturn(java.util.Optional.of(ticket));
        assertThat(ticketBookingService.getTicketById(1).get()).isEqualTo(ticket);

    }

    //get all tickets
    @Test
    public void testingGetAllBookedTickets(){
        Ticket mockTicket1 = new Ticket();
        mockTicket1.setTicketId(3);
        mockTicket1.setPassengerName("Kassahun Assfaw");
        mockTicket1.setSourceStation("Addis Ababa");
        mockTicket1.setDestStation("Atlanta");
        mockTicket1.setBookingDate(new Date());
        mockTicket1.setEmail("kassamsay@gmail.com");

        Ticket mockTicket2 = new Ticket();
        mockTicket2.setTicketId(4);
        mockTicket2.setPassengerName("Getaneh Yilma");
        mockTicket2.setSourceStation("Addis Ababa");
        mockTicket2.setDestStation("Dallas");
        mockTicket2.setBookingDate(new Date());
        mockTicket2.setEmail("erefit2@gmail.com");

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(mockTicket1);
        ticketList.add(mockTicket2);

        Mockito.when(ticketBookingDao.findAll()).thenReturn(ticketList);

        assertThat(ticketBookingService.getAllBookedTickets()).isEqualTo(ticketList);

    }

    //deleting ticket
    @Test
    public void testingDeletingTicket(){
        Ticket ticket = new Ticket();
        ticket.setTicketId(4);
        ticket.setPassengerName("Kassahun Assfaw");
        ticket.setSourceStation("Addis Ababa");
        ticket.setDestStation("Atlanta");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kassamsay@gmail.com");
        Mockito.when(ticketBookingDao.findById(4)).thenReturn(java.util.Optional.of(ticket));
        Mockito.when(ticketBookingDao.existsById(4)).thenReturn(false);
        assertFalse(ticketBookingDao.existsById(4));


    }

    //testing updating tickets
    @Test
    public void testUpdateTicket(){
        Ticket ticket = new Ticket();
        ticket.setTicketId(4);
        ticket.setPassengerName("Kassahun Assfaw");
        ticket.setSourceStation("Addis Ababa");
        ticket.setDestStation("Atlanta");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kassamsay@gmail.com");

        Mockito.when(ticketBookingDao.findById(4)).thenReturn(java.util.Optional.of(ticket));

        ticket.setEmail("kassahun.assfaw@gmail.com");
        Mockito.when(ticketBookingDao.save(ticket)).thenReturn(ticket);

        assertThat(ticketBookingService.updateTicket(4, "kassahun.assfaw@gmail.com")).isEqualTo(ticket);

    }

    //testing finding ticket by email
    @Test
    public void findingTicketByEmail(){
        Ticket ticket = new Ticket();
        ticket.setTicketId(4);
        ticket.setPassengerName("Kassahun Assfaw");
        ticket.setSourceStation("Addis Ababa");
        ticket.setDestStation("Atlanta");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kassamsay@gmail.com");

        Mockito.when(ticketBookingDao.findByEmail("kassamsay@gmail.com")).thenReturn(ticket);
        assertThat(ticketBookingService.getTicketByEmail("kassamsay@gmail.com")).isEqualTo(ticket);
    }

}