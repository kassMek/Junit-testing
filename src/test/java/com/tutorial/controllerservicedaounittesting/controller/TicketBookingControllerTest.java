package com.tutorial.controllerservicedaounittesting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.controllerservicedaounittesting.entities.Ticket;
import com.tutorial.controllerservicedaounittesting.service.TicketBookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@WebMvcTest(value=TicketBookingController.class)
public class TicketBookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketBookingService ticketBookingService;

    //testing create ticket
    @Test
    public void testCreateTicket() throws Exception {

        Ticket mockTicket = new Ticket();
        mockTicket.setTicketId(1);
        mockTicket.setPassengerName("Kassahun Assfaw");
        mockTicket.setSourceStation("Addis Ababa");
        mockTicket.setDestStation("Atlanta");
        //mockTicket.setBookingDate(new Date());
        mockTicket.setEmail("kassamsay@gmail.com");

        String inputInJson = this.mapToJson(mockTicket);
        System.out.println(inputInJson);

        String URI = "/api/tickets/create";

        Mockito.when(ticketBookingService.createTicket(Mockito.any(Ticket.class))).thenReturn(mockTicket);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        verify(ticketBookingService, Mockito.times(1)).createTicket(Mockito.any(Ticket.class));
    }



    //testing get ticket byId

    @Test
    public void testGetTicketById() throws Exception {
        Ticket mockTicket = new Ticket();
        mockTicket.setTicketId(1);
        mockTicket.setPassengerName("Martin Bingel");
        mockTicket.setSourceStation("Kolkata");
        mockTicket.setDestStation("Delhi");
        //mockTicket.setBookingDate(new Date());
        mockTicket.setEmail("martin.s2017@gmail.com");

        Mockito.when(ticketBookingService.getTicketById(Mockito.anyInt())).thenReturn(java.util.Optional.of(mockTicket));

        String URI = "/api/tickets/ticketId/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(mockTicket);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }


    @Test
    public void testGetAllBookedTickets() throws Exception {

        Ticket mockTicket1 = new Ticket();
        mockTicket1.setTicketId(3);
        mockTicket1.setPassengerName("Kassahun Assfaw");
        mockTicket1.setSourceStation("Addis Ababa");
        mockTicket1.setDestStation("Atlanta");
        //mockTicket1.setBookingDate(new Date());
        mockTicket1.setEmail("kassamsay@gmail.com");

        Ticket mockTicket2 = new Ticket();
        mockTicket2.setTicketId(4);
        mockTicket2.setPassengerName("Getaneh Yilma");
        mockTicket2.setSourceStation("Addis Ababa");
        mockTicket2.setDestStation("Dallas");
        //mockTicket2.setBookingDate(new Date());
        mockTicket2.setEmail("erefit2@gmail.com");

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(mockTicket1);
        ticketList.add(mockTicket2);

        Mockito.when(ticketBookingService.getAllBookedTickets()).thenReturn(ticketList);

        String URI = "/api/tickets/alltickets";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(ticketList);
       //System.out.println(expectedJson);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);

        verify(ticketBookingService, Mockito.times(1)).getAllBookedTickets();

    }


    // //Testing finding ticket by email
 @Test
 public void testGetTicketByEmail() throws Exception {
     Ticket mockTicket = new Ticket();
     mockTicket.setTicketId(5);
     mockTicket.setPassengerName("Birhanu Moges");
     mockTicket.setSourceStation("Mortule Mariam");
     mockTicket.setDestStation("Addis Ababa");
     //mockTicket.setBookingDate(new Date());
     mockTicket.setEmail("biresanjaw@gmail.com");

     String expectedJson = this.mapToJson(mockTicket);
     //System.out.println(expectedJson);

     Mockito.when(ticketBookingService.getTicketByEmail(Mockito.anyString())).thenReturn(mockTicket);

     String URI = "/api/tickets/email/biresanjaw@gmail.com";
     RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
             URI).accept(
             MediaType.APPLICATION_JSON);

     MvcResult result = mockMvc.perform(requestBuilder).andReturn();
     String outputInJson = result.getResponse().getContentAsString();
     assertThat(outputInJson).isEqualTo(expectedJson);

 }



    /**
     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     */
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }


}