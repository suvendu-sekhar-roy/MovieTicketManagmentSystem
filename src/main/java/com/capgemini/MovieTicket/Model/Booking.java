package com.capgemini.MovieTicket.Model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TicketBooking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ticketId;

    private Integer showId;

    //@DateTimeFormat
   // @GeneratedValue(strategy = GenerationType.AUTO)
   // private LocalDateTime bookingDate;

    private Integer transactionId;
   // private String transactionMode;

    private Double totalCost;
    //private Ticket ticket;
}
