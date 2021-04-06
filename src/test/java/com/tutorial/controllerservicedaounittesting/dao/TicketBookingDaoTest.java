package com.tutorial.controllerservicedaounittesting.dao;

import com.tutorial.controllerservicedaounittesting.entities.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TicketBookingDaoTest {

    @Autowired
  private TestEntityManager entityManager;
    @Autowired
   private TicketBookingDao ticketBookingDao;

    //testing saving ticket
    @Test
    public void testingSavingTicket(){
        Ticket ticket=getTicket();
        Ticket savedInDb = entityManager.persist(ticket);
        Optional<Ticket> getFromDb = ticketBookingDao.findById(savedInDb.getTicketId());
        assertThat(getFromDb.get()).isEqualTo(savedInDb);

    }

     //testing all booked tickets
     @Test
     public void testGetAllBookedTickets(){
         Ticket mockTicket1 = new Ticket();
         mockTicket1.setPassengerName("Kassahun Assfaw");
         mockTicket1.setSourceStation("Addis Ababa");
         mockTicket1.setDestStation("Atlanta");
         mockTicket1.setBookingDate(new Date());
         mockTicket1.setEmail("kassamsay@gmail.com");

         Ticket mockTicket2 = new Ticket();
         mockTicket2.setPassengerName("Getaneh Yilma");
         mockTicket2.setSourceStation("Addis Ababa");
         mockTicket2.setDestStation("Dallas");
         mockTicket2.setBookingDate(new Date());
         mockTicket2.setEmail("erefit2@gmail.com");

         entityManager.persist(mockTicket1);
         entityManager.persist(mockTicket2);

         Iterable<Ticket> allTicketsFromDb=ticketBookingDao.findAll();
         List<Ticket> ticketList= new ArrayList<>();
          for(Ticket tt:allTicketsFromDb){
              ticketList.add(tt);
          }
        assertThat(ticketList.size()).isEqualTo(2);

     }

      //test find by Email

    @Test
    public void testingByEmail(){
        Ticket ticket = new Ticket();
        //ticket.setTicketId(4);
        ticket.setPassengerName("Kassahun Assfaw");
        ticket.setSourceStation("Addis Ababa");
        ticket.setDestStation("Atlanta");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kassamsay@gmail.com");

        entityManager.persist(ticket);
        Ticket fromDb=ticketBookingDao.findByEmail(ticket.getEmail());
        assertThat(fromDb.getEmail()).isEqualTo("kassamsay@gmail.com");
        assertThat(fromDb.getDestStation()).isEqualTo("Atlanta");

    }

    //testing updating ticket

    @Test
    public void testingUpdateTicket(){
        Ticket ticket = new Ticket();
        ticket.setPassengerName("Martin Bingel");
        ticket.setSourceStation("Kolkata");
        ticket.setDestStation("Delhi");
        ticket.setBookingDate(new Date());
        ticket.setEmail("martin.s2017@gmail.com");

        //save Ticket info in DB
        entityManager.persist(ticket);

        Ticket getFromDb = ticketBookingDao.findByEmail("martin.s2017@gmail.com");
        getFromDb.setEmail("kassfaw@miu.edu");
        entityManager.persist(getFromDb);
        assertThat(getFromDb.getEmail()).isEqualTo("kassfaw@miu.edu");

    }
    //stand alone ticket
    private Ticket getTicket() {
        Ticket ticket = new Ticket();
        ticket.setPassengerName("Abrham Tilahun");
        ticket.setSourceStation("Dessie");
        ticket.setDestStation("Silver Spring");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kassmasay@gmail.com");
        return ticket;
    }
}