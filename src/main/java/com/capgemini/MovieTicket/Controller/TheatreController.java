package com.capgemini.MovieTicket.Controller;

import java.util.List;

import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Booking;
import com.capgemini.MovieTicket.Model.Movie;
import com.capgemini.MovieTicket.Model.Theatre;
import com.capgemini.MovieTicket.Service.TheatreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/theatre")
public class TheatreController {
	Logger logger = LoggerFactory.getLogger(TheatreController.class);
	@Autowired
	private TheatreService theatreservice;


	/*
	 * 
	 * @return listOfTheatres
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Theatre>> getAlltheatres() throws RecordNotFoundException {

		logger.info("-------Theatre List Fetched---------");
		return ResponseEntity.ok(theatreservice.getAllTheatres());
	}

	/*
	 * 
	 * @param t
	 * @return inserted theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@PostMapping("/insert")
	public ResponseEntity<Theatre> addTheatre(@RequestBody Theatre t) throws RecordNotFoundException {

		//logger.info("-------Theatre Added Successfully---------");
		return new ResponseEntity<>(theatreservice.addTheatre(t), HttpStatus.CREATED);
	}

	/*
	 * 
	 * @param t
	 * @return updated theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@PutMapping("/update")
	public ResponseEntity<Theatre> updateTheatre(@RequestBody Theatre t) throws  RecordNotFoundException {

		//logger.info("-------Theatre Updated Successfully---------");
		Theatre theatre= theatreservice.updateTheatre(t);
		return new ResponseEntity<>(theatre, HttpStatus.CREATED);
	}

	/*
	 * 
	 * @param theatreId
	 * @return theatre by theatreId
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@GetMapping("/find/{theatreId}")
	public ResponseEntity<Theatre> findTheatre(@PathVariable int theatreId)
			throws  RecordNotFoundException {

		logger.info("-------Theatre Found with Theatre id" + theatreId + "---------");
		return ResponseEntity.ok(theatreservice.findTheatres(theatreId));
	}

	/*
	 * 
	 * @param theatreId
	 * @return deleted theatre
	 * @throws AccessForbiddenException
	 * @throws TheatreNotFoundException
	 */
	@DeleteMapping("/delete/{theatreId}")
	public ResponseEntity<Theatre> deleteMoviesById(@PathVariable int theatreId) throws RecordNotFoundException {

			Theatre theatre = theatreservice.findTheatres(theatreId);
		if(theatre==null){
			theatreservice.deleteTheatreById(theatreId);
			return  new ResponseEntity<Theatre>(theatre, HttpStatus.OK);
		}
		//logger.info("-------Theatre Deleted with Theatre id" + theatreId + "---------");
		else{
			return  new ResponseEntity<Theatre>( HttpStatus.NOT_IMPLEMENTED);
		}
	}
	
	@GetMapping("/findbyMovie/{movieId}")
	public ResponseEntity<List<Theatre>> findTheatreByMovieId(@PathVariable int movieId)
			throws  RecordNotFoundException {
		return ResponseEntity.ok(theatreservice.findTheatresByMovie(movieId));
	}
	
}
