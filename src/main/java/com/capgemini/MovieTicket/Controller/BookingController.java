package com.capgemini.MovieTicket.Controller;


import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Booking;
import com.capgemini.MovieTicket.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/newBooking")
    public ResponseEntity<Booking> addTicketBooking(@RequestBody Booking booking){
        //Also include movieid, theater id, showtime
        Booking bkList =null;
        bkList = bookingService.addBooking(booking);
        return new ResponseEntity<Booking>(bkList, HttpStatus.CREATED);
    }

    @PostMapping(value = "/updateBooking")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Booking> modifyBooking(@RequestBody Booking updateBooking) {

        Booking bkList = bookingService.updateBooking(updateBooking);
        return new ResponseEntity<Booking>(bkList, HttpStatus.OK);
    }

    @GetMapping("/showAllBooking")
    public List<Booking> showAll(){
        List<Booking> bkList = bookingService.showAllBooking();
        return bkList;
    }
    @GetMapping("/byDate/{date}")
    public List<Booking> getBookingByDate(@PathVariable("date") LocalDate date){
        List<Booking> bkList = bookingService.showBookingListbyDate(date);
        return bkList;
    }

    @DeleteMapping("/cancelBooking/{bookingId}")
    @ExceptionHandler(RecordNotFoundException.class)
    public void deleteBookingByID(@PathVariable("bookingId") Integer bookingId) {

        bookingService.cancelBooking(bookingId);
    }
}
