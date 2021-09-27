package com.capgemini.MovieTicket.Model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="seat")
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int seatId;
	private String seatNumber;
	private String type;
	private double price;

	@Enumerated(EnumType.STRING)
	private SeatStatus status;
	/*@JsonIgnore
	@ManyToOne
	private Ticket ticket;
	*/
}
