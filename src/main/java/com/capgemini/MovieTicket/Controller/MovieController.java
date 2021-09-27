package com.capgemini.MovieTicket.Controller;

import com.capgemini.MovieTicket.Exception.RecordAlreadyExistException;
import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Movie;
import com.capgemini.MovieTicket.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Movie Controller Administrator can Create,Read,Update,Delete Movie Records
 * and can also find Movies based on Date and Theater.
 * 
*/
@RestController
@RequestMapping("/movies")
public class MovieController {

	//Logger logger = LoggerFactory.getLogger(MoviesController.class);
	@Autowired
	private MovieService moviesService;

	/*
	  Stores a Movie object in the Database.
	 *
	 * @param movie
	 * @return Movie
	 * @throws MovieNotFoundException
	 * @throws IOException 
	 * @throws AccessForbiddenException
	 */
	@PostMapping("/add")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) throws RecordAlreadyExistException {
		movie = moviesService.addMovie(movie);
		//logger.info("-------Movie Added Successfully---------");
		return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
	}

	/*
	 * Updates a existing Movie record in the database.
	 * 
	 * @param movie
	 * @return Movie
	 * @throws MovieNotFoundException
	 * @throws AccessForbiddenException
	 */
	@PutMapping("/update")
	public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) throws RecordNotFoundException {

		ResponseEntity<Movie> response = null;
		if (movie == null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			movie = moviesService.updateMovie(movie);
			response = new ResponseEntity<>(movie, HttpStatus.OK);
			//logger.info("-------Movie Updated Successfully---------");
		}
		return response;
	}

	/*
	//Update movie to showlists
	@PutMapping("/map")
	public ResponseEntity<Movie> addToShow(@RequestBody Movie movie,@RequestParam(required = false) Integer showId)
			throws MovieNotFoundException {

		ResponseEntity<Movie> response = null;
		if (movie == null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			movie = moviesService.addMovieToShow(movie,showId);
			response = new ResponseEntity<>(movie, HttpStatus.OK);
			logger.info("-------Movie Updated Successfully---------");
		}
		return response;
	}
*/
	
	/*
	 * Return's the List of Movies from the Database
	 * 
	 * @return List<Movie>
	 * @throws MovieNotFoundException
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/findall")
	public ResponseEntity<List<Movie>> viewMovieList() throws RecordNotFoundException {

		//logger.info("-------Movie List Fetched---------");
		return ResponseEntity.ok(moviesService.viewMovieList());
	}

	/*
	 * Returns the record from the database using identifier - movieId
	 * 
	 * @param movieId
	 * @return Movie
	 * @throws MovieNotFoundException
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/viewMovie/{movieId}")
	public ResponseEntity<Movie> viewMovie(@PathVariable int movieId) throws RecordNotFoundException {

		ResponseEntity<Movie> response = null;
		try {
			Movie movie = moviesService.viewMovie(movieId);
			response = new ResponseEntity<>(movie, HttpStatus.OK);
			//logger.info("-------Movie With Movie id " + movieId + " Found---------");
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new RecordNotFoundException("Movie with " + movieId + " id dosen't exist");
		}
		return response;
		// return ResponseEntity.ok(moviesService.viewMovie(movieId));
	}

	/*
	 * Removes persisted Movie instance from the Database.
	 * 
	 * @param movieId
	 * @return Movie
	 * @throws MovieNotFoundException
	 * @throws AccessForbiddenException
	 */
	@DeleteMapping("/delete/{movieId}")
	public ResponseEntity<Movie> removeMovie(@PathVariable int movieId)
			throws RecordNotFoundException {

		ResponseEntity<Movie> response = null;
		Movie movie = moviesService.viewMovie(movieId);
		if (movie == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			moviesService.removeMovie(movieId);
			response = new ResponseEntity<>(movie, HttpStatus.OK);
			//logger.info("-------Movie With Movie id " + movieId + " Deleted---------");
		}
		return response;
	}

	/*
	 * Displays List of movies based on the TheatreId.
	 * 
	 * @param theatreId
	 * @return Movie
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/byTheatre/{theatreId}")
	public List<Movie> viewMovieByTheatreId(@PathVariable int theatreId)  {
		//logger.info("-------Movies With TheatreId " + theatreId + " Found---------");
		return moviesService.viewMovieList(theatreId);
	}

	/*
	 * Returns the list of Movies based on the Date.
	 * 
	 * @param date
	 * @return Movie
	 * @throws AccessForbiddenException
	 */
	@GetMapping("/byDate/{date}")
	public List<Movie> viewMovieByLocalDate(
			@RequestParam("movieDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		//logger.info("-------Movies With Date " + date + " Found---------");
		return moviesService.viewMovieList(date);
	}

}
