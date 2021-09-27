package com.capgemini.MovieTicket.Service;

import java.util.List;

import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Ticket;


public interface TicketService {
	public Ticket addTicket(Ticket ticket, Integer bookingId) throws RecordNotFoundException;

	public Ticket findTicket(int ticketId);

	List<Ticket> viewTicketList() throws RecordNotFoundException;

}
