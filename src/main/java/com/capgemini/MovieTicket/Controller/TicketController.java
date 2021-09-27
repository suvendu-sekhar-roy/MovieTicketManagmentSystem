package com.capgemini.MovieTicket.Controller;

import java.util.List;

import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Ticket;
import com.capgemini.MovieTicket.Service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * 
 * @author Yogendra
 *
 */
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tickets")
public class TicketController {

	Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	private TicketService ticketService;
	/*@Autowired
	LoginController loginController;
*/
	/*
	 * 
	 * @param ticket
	 * @return added ticket

	 */
	@PostMapping("/add")
	public ResponseEntity<Ticket> addATicket(@RequestBody Ticket ticket, @RequestParam(required = false) Integer bookingId)
			throws RecordNotFoundException {
		//AccessForbiddenException,
		ticket = ticketService.addTicket(ticket,bookingId);
		/* System.out.println(ticket.getSeats()); */
		logger.info("-------Ticked Created Successfully---------");
		return new ResponseEntity<>(ticket, HttpStatus.CREATED);
	}

	/*
	 * 
	 * @return ticketList
	 * @throws AccessForbiddenException
	 * @throws TicketNotFoundException
	 */
	@GetMapping("/findall")
	public ResponseEntity<List<Ticket>> viewTicketList() throws RecordNotFoundException {//AccessForbiddenException,
		
		logger.info("-------List of Tickets Found Successfully---------");
		return ResponseEntity.ok(ticketService.viewTicketList());
	}

	/*
	 * 
	 * @param ticketId
	 * @return ticket by ticketId
	 * @throws TicketNotFoundException
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/{ticketId}")
	public Ticket findATicket(@PathVariable int ticketId) throws RecordNotFoundException{	 //, AccessForbiddenException
		Ticket t = null;
		try {
			t = ticketService.findTicket(ticketId);
			logger.info("-------Ticket with ticketId " + ticketId + " Foound Successfully---------");
		} catch (Exception e) {
			throw new RecordNotFoundException("Invalid Ticket ID");
		}
		return t;

	}

}
