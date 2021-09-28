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

    //Generate a new Booking
    @PostMapping(value = "/addBooking")
    public ResponseEntity<Booking> addTicketBooking(@RequestBody Booking booking,
                                                    @RequestParam(required = false) Integer customerId,
                                                    @RequestParam(required = false) Integer showId){
        //Also include movieid, theater id
        Booking bkList =null;
        bkList = bookingService.addBooking(booking, customerId, showId);
        return new ResponseEntity<Booking>(bkList, HttpStatus.CREATED);
    }

    //Update booking
    @PostMapping(value = "/updateBooking")
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Booking> modifyBooking(@RequestBody Booking updateBooking) {

        Booking bkList = bookingService.updateBooking(updateBooking);
        return new ResponseEntity<Booking>(bkList, HttpStatus.OK);
    }

    //show all booking
    @GetMapping("/showAll")
    public List<Booking> showAll(){
        List<Booking> bkList = bookingService.showAllBooking();
        return bkList;
    }

    //show all booking of a particular Date
    @GetMapping("/showByDate/{date}")
    public List<Booking> getBookingByDate(@PathVariable("date") LocalDate date){
        List<Booking> bkList = bookingService.showBookingListbyDate(date);
        return bkList;
    }

    //Get total cost of a booking
    @GetMapping("/cost/{bookingId}")
    public double TotalBookingCost(@PathVariable int bookingId) throws RecordNotFoundException {

        return bookingService.totalCost(bookingId);
    }

    //Cancel a booking
    @DeleteMapping("/cancelBooking/{bookingId}")
    @ExceptionHandler(RecordNotFoundException.class)
    public void deleteBookingByID(@PathVariable("bookingId") Integer bookingId) {

        bookingService.cancelBooking(bookingId);
    }
}
