package com.capgemini.MovieTicket.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capgemini.MovieTicket.Exception.RecordAlreadyExistException;
import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Movie;
import com.capgemini.MovieTicket.Model.Show;
import com.capgemini.MovieTicket.Repository.MovieRepository;
import com.capgemini.MovieTicket.Repository.ShowRepository;
import com.capgemini.MovieTicket.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.util.StringUtils;
import java.io.IOException;
import java.util.Base64;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movierepository;

	@Autowired
	TheatreRepository theatreRepository;
	@Autowired
	ShowRepository showrepository;
	/*@Autowired
	QueryClass query;*/

	@Override
	public Movie addMovie(Movie movie) throws RecordNotFoundException {

			if (movierepository.existsById(movie.getMovieId())) {
				throw new RecordAlreadyExistException("Movie with this id already exists");
			}
			else {
				movierepository.save(movie);
			}
		return movie;
	}
		//We have to delete all the shows also with movie deletion

	@Override
	public Movie removeMovie(int movieid) {
		Movie m = movierepository.findById(movieid).get();
		List<Show> shows = showrepository.findAll();
		for (Show show : shows) {
			if (show.getMovie()!=null && show.getMovie().getMovieId() == movieid) {
				show.setMovie(null);
			}
		}
		movierepository.delete(m);
		return m;
	}
	
	@Override
	public Movie updateMovie(Movie movie) {
		if (movie != null) {
			if (movierepository.existsById(movie.getMovieId())) {
				movierepository.save(movie);
			} else {

				throw new RecordNotFoundException("Movie with this id doesn't exist");
			}
		}
		return movie;
	}


	//We have to add shows according to movie
	@Override
	public Movie addMovieToShow(Movie movie,Integer showId) throws RecordNotFoundException{
		Show show=new Show();
		if (showId != null) {
			show = showrepository.getById(showId);
			movie.setShow(show);
		}
		movierepository.save(movie);
		return movierepository.getById(movie.getMovieId());
	}

	@Override
	public Movie viewMovie(int movieid) {
		return movierepository.findById(movieid).get();
	}

	@Override
	public List<Movie> viewMovieList() throws RecordNotFoundException {
		List<Movie> ml = (List<Movie>) movierepository.findAll();
		if (ml.size() == 0) throw new RecordNotFoundException("No movie is going on");
		return ml;
	}

	//Show movie my theater id
	@Override
	public List<Movie> viewMovieList(int theatreid) {
		List<Movie> movies = new ArrayList<>();
		List<Show> shows = showrepository.findAll();
		Set<Integer> showIds = new HashSet<>();
		for (Show s : shows) {
			if (s.getTheatre().getTheatreId() == theatreid) {
				showIds.add(s.getShowId());
			}
		}
		for (Integer id : showIds) {
			movies.add(showrepository.getById(id).getMovie());
		}
		return movies;
	}


	@Override
	public List<Movie> viewMovieList(LocalDate date) {
		List<Movie> mvList = new ArrayList<>();
		for (Movie movie : movierepository.findAll()) {
			if (movie.getMovieDate() != null && movie.getMovieDate().isEqual(date)) {
				mvList.add(movie);
			}
		}
		return mvList;
	}

}
