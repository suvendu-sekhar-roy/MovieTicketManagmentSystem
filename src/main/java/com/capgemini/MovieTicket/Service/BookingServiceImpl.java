package com.capgemini.MovieTicket.Service;

import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Booking;
import com.capgemini.MovieTicket.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    @Override
    public Booking addBooking(Booking newbooking) {
        bookingRepository.save(newbooking);
        //return bookingRepository.findById(newbooking.getTransactionId()).get();
        return newbooking;
    }

    @Override
    public Booking updateBooking(Booking changedBooking) {
        Optional<Booking> findBookingById = bookingRepository.findById(changedBooking.getTicketId());
        if (findBookingById.isPresent()) {
            bookingRepository.save(changedBooking);
        } else
            throw new RecordNotFoundException("Booking with Booking Id: " + changedBooking.getTicketId() + " not exists!!");
        return changedBooking;
    }

    //Show all bookings using date
    @Override
    public List<Booking> showBookingListbyDate(LocalDateTime bookingdate) throws RecordNotFoundException{
       /* List<Booking> bkList = new ArrayList<>();
        for (Booking booking : bookingRepository.findAll()) {
            if (booking.getBookingDate() != null && booking.getBookingDate().isEqual(bookingdate)) {
                bkList.add(booking);
            }
        }
        if (bkList.size() == 0)
            throw new RecordNotFoundException("No bookings found");
        else {
            return bkList;
        }*/
        return null;
    }

    public List<Booking> showAllBooking(){
        return (List<Booking>) bookingRepository.findAll();

    }

    //Show all bookings using MovieId
    @Override
    public List<Booking> showBookingListbyId(Integer movieId) {
        /*List<Booking> bk = query.getAllByMovieId(movieid);
        return bk;*/
        return null;
    }

    @Override
    public Booking totalCost(Integer bookingId) {
        /*
        List<Ticket> tickets = ticketRepository.findAll();
		Set<Seat> Seats = new HashSet<>();
		for (Ticket ticket : tickets) {
			if (bookingid == ticket.getBooking().getTransactionId()) {
				Seats.addAll(ticket.getSeats());
			}
		}
		double amount = 0;
		for (Seat seat : Seats) {
			amount = amount + seat.getPrice();
		}
		Booking booking = bookingRepository.getOne(bookingid);
		booking.setTotalCost(amount);
		bookingRepository.saveAndFlush(booking);
		return amount;
         */
        return null;
    }

    @Override
    public String cancelBooking(Integer ticketId) throws RecordNotFoundException {
        Optional<Booking> findBookingById = bookingRepository.findById(ticketId);

        if (findBookingById.isPresent()) {
            bookingRepository.deleteById(ticketId);
            return "Booking Canceled!!";
        } else {
            throw new RecordNotFoundException("Booking with Ticket Id: " + ticketId + " not exists!!");

        }
    }
}
