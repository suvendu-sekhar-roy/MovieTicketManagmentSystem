package com.capgemini.MovieTicket.Service;

import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Booking;
import com.capgemini.MovieTicket.Model.Seat;
import com.capgemini.MovieTicket.Model.Ticket;
import com.capgemini.MovieTicket.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    CustomerRepository custoRepository;
    @Autowired
    TicketRepository ticketRepository;

    @Override
    public Booking addBooking(Booking newbooking) {
        bookingRepository.save(newbooking);
        //return bookingRepository.findById(newbooking.getTransactionId()).get();
        return newbooking;
    }

    @Override
    public Booking updateBooking(Booking changedBooking) {
        Optional<Booking> findBookingById = bookingRepository.findById(changedBooking.getBookingId());
        if (findBookingById.isPresent()) {
            bookingRepository.save(changedBooking);
        } else
            throw new RecordNotFoundException("Booking with Booking Id: " + changedBooking.getBookingId() + " not exists!!");
        return changedBooking;
    }

    //Show all bookings using date
    @Override
    public List<Booking> showBookingListbyDate(LocalDate bookingdate) throws RecordNotFoundException{
        List<Booking> bkList = new ArrayList<>();
        for (Booking booking : bookingRepository.findAll()) {
            if (booking.getBookingDate() != null && booking.getBookingDate().isEqual(bookingdate)) {
                bkList.add(booking);
            }
        }
        if (bkList.size() == 0)
            throw new RecordNotFoundException("No bookings found");
        else {
            return bkList;
        }
    }

    public List<Booking> showAllBooking(){
        return (List<Booking>) bookingRepository.findAll();

    }

    //Show all bookings using MovieId
    @Override
    public List<Booking> showBookingbyId(Integer movieId) {
        /*List<Booking> bk = query.getAllByMovieId(movieid);
        return bk;*/
        return null;
    }

    @Override
    public double totalCost(Integer bookingId) {

        List<Ticket> tickets = ticketRepository.findAll();
		Set<Seat> Seats = new HashSet<>();
		for (Ticket ticket : tickets) {
			if (bookingId == ticket.getBooking().getBookingId()) {
				Seats.addAll(ticket.getSeats());
			}
		}
		double amount = 0;
		for (Seat seat : Seats) {
			amount = amount + seat.getPrice();
		}
		Booking booking = bookingRepository.getById(bookingId);
		booking.setTotalCost(amount);
		bookingRepository.saveAndFlush(booking);
		return amount;
    }

    @Override
    public String cancelBooking(Integer bookingId) throws RecordNotFoundException {
        Optional<Booking> findBookingById = bookingRepository.findById(bookingId);

        if (findBookingById.isPresent()) {
            bookingRepository.deleteById(bookingId);
            return "Booking Canceled!!";
        } else {
            throw new RecordNotFoundException("Booking with Ticket Id: " + bookingId + " not exists!!");

        }
    }
}
