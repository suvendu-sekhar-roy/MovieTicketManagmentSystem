package com.capgemini.MovieTicket.Controller;

import java.util.List;

import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Seat;
import com.capgemini.MovieTicket.Service.SeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/seats")
public class SeatController {

	Logger logger = LoggerFactory.getLogger(SeatController.class);
	@Autowired
	private SeatService seatService;

	@PostMapping("/addSeat")
	public ResponseEntity<Seat> addASeat(@RequestBody Seat seat)
			throws RecordNotFoundException {
		//AccessForbiddenException,
		seat = seatService.addSeat(seat);
		logger.info("-------Seat Added Successfully---------");
		return new ResponseEntity<>(seat, HttpStatus.CREATED);
	}

	@PutMapping("/updateSeat")
	public ResponseEntity<Seat> updateSeat(@RequestBody Seat seat)
			throws RecordNotFoundException { //AccessForbiddenException,

		ResponseEntity<Seat> response = null;
		if (seat == null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			seat = seatService.updateSeat(seat);
			response = new ResponseEntity<>(seat, HttpStatus.OK);
			logger.info("-------Seat Updated Successfully---------");
		}
		return response;
	}

	@GetMapping("/showAll")
	public ResponseEntity<List<Seat>> viewSeatList() throws  RecordNotFoundException { //AccessForbiddenException,
		
		logger.info("-------List of Seats Fetched Successfully---------");
		return ResponseEntity.ok(seatService.viewSeatList());
	}

	@PutMapping("/book")
	public ResponseEntity<Seat> BookASeat(@RequestBody Seat seat)
			throws RecordNotFoundException {//AccessForbiddenException,
		
		seat = seatService.bookSeat(seat);
		logger.info("-------Seat booking Successfull---------");
		return new ResponseEntity<>(seat, HttpStatus.OK);
	}


	@PutMapping("/cancelSeat")
	public ResponseEntity<Seat> CancelASeat(@RequestBody Seat seat)
			throws  RecordNotFoundException { //AccessForbiddenException,
		
		seat = seatService.cancelSeatBooking(seat);
		logger.info("-------Seat Cancellation Successfull---------");
		return new ResponseEntity<>(seat, HttpStatus.OK);
	}


	@PutMapping("/block")
	public ResponseEntity<Seat> BloclASeat(@RequestBody Seat seat)
			throws  RecordNotFoundException { //AccessForbiddenException,
		
		seat = seatService.blockSeat(seat);
		logger.info("-------Seat blocking Successfull---------");
		return new ResponseEntity<>(seat, HttpStatus.OK);

	}
}
