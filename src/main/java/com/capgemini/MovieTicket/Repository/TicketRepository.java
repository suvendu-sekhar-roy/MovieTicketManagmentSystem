package com.capgemini.MovieTicket.Repository;

import com.capgemini.MovieTicket.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
