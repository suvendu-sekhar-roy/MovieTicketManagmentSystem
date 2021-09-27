package com.capgemini.MovieTicket.Service;

import java.util.List;

import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Booking;
import com.capgemini.MovieTicket.Model.Seat;
import com.capgemini.MovieTicket.Model.Ticket;
import com.capgemini.MovieTicket.Repository.BookingRepository;
import com.capgemini.MovieTicket.Repository.SeatRepository;
import com.capgemini.MovieTicket.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketServiceImpl implements TicketService {
	@Autowired
	private TicketRepository ticketRepository;

	/*public TicketServiceImpl(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}
	*/
	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public Ticket addTicket(Ticket ticket, Integer bookingId) throws RecordNotFoundException {
		Booking booking=new Booking();
		if(bookingId!=null) {
			booking=bookingRepository.findById(bookingId).get();
			booking.setTransactionStatus("Completed");
			ticket.setBooking(booking);
		}
		ticketRepository.save(ticket);

		  for(Seat s:ticket.getSeats()) {
			  s.setTicket(ticket);
		  	seatRepository.save(s);
		  }

		return ticket;
	}

	@Override
	public List<Ticket> viewTicketList() throws RecordNotFoundException {
		List<Ticket> ti = ticketRepository.findAll();
		if (ti.size() == 0)
			throw new RecordNotFoundException("No tickets are avaliable");
		return ti;
	}

	@Override
	public Ticket findTicket(int ticketId) {

		return ticketRepository.findById(ticketId).get();
	}

}
