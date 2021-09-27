package com.capgemini.MovieTicket.Service;

import com.capgemini.MovieTicket.Exception.RecordAlreadyExistException;
import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Seat;

import java.util.List;

public interface SeatService {
    public Seat addSeat(Seat seat) throws RecordAlreadyExistException;

    public List<Seat> viewSeatList() throws RecordNotFoundException;

    public Seat updateSeat(Seat seat);

    public Seat bookSeat(Seat seat);

    public Seat cancelSeatBooking(Seat seat);

    public Seat blockSeat(Seat seat); // not available for any booking
}
