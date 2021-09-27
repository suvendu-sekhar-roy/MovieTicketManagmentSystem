package com.capgemini.MovieTicket.Service;

import java.util.List;

import com.capgemini.MovieTicket.Exception.RecordAlreadyExistException;
import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Seat;
import com.capgemini.MovieTicket.Model.SeatStatus;
import com.capgemini.MovieTicket.Repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {
	@Autowired
	private SeatRepository seatRepository;

	@Override
	public Seat addSeat(Seat seat) throws RecordAlreadyExistException {
		if (seat != null) {
			if (seatRepository.existsById(seat.getSeatId())) {
				throw new RecordAlreadyExistException("Seat with this id already exists");
			} else {
				seatRepository.save(seat);
			}
		}
		//return seatRepository.getOne(seat.getSeatId());
		return seat;
	}

	@Override
	public List<Seat> viewSeatList() throws RecordNotFoundException {
		List<Seat> li = (List<Seat>) seatRepository.findAll();
		/*
		 * if (li.size() == 0) throw new SeatNotFoundException("No seats found");
		 */
		return li;
	}

	@Override
	public Seat updateSeat(Seat seat) {
		// TODO Auto-generated method stub
		return seatRepository.save(seat);
	}

	@Override
	public Seat bookSeat(Seat seat) {
		seat.setStatus(SeatStatus.BOOKED);
		return seatRepository.save(seat);
	}

	@Override
	public Seat cancelSeatBooking(Seat seat) {
		seat.setStatus(SeatStatus.CANCELLED);
		return seatRepository.save(seat);
	}

	@Override
	public Seat blockSeat(Seat seat) {
		seat.setStatus(SeatStatus.BLOCKED);
		return seatRepository.save(seat);
	}

}
