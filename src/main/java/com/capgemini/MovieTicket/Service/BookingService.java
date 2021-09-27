package com.capgemini.MovieTicket.Service;

import com.capgemini.MovieTicket.Model.Booking;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface BookingService {
    public Booking addBooking(Booking booking);
    public Booking updateBooking(Booking booking);
    public List<Booking> showAllBooking();
    public List<Booking> showBookingListbyDate(LocalDateTime date);
    public List<Booking> showBookingListbyId(Integer ticketId);
    public Booking totalCost(Integer bookingId);
    public String cancelBooking(Integer ticketId); //throws Exception;
}
