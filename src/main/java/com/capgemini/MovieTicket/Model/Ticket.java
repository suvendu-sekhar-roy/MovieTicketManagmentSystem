package com.capgemini.MovieTicket.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ticketId;
	private int noOfSeats;
	private boolean ticketStatus;
	@OneToMany(mappedBy = "ticket")
	private List<Seat> seats;
	@OneToOne
	private Booking booking;


}