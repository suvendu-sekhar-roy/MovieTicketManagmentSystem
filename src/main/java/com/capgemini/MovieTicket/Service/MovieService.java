package com.capgemini.MovieTicket.Service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.MovieTicket.Exception.RecordAlreadyExistException;
import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Movie;

public interface MovieService {

	public Movie addMovie(Movie movie) throws RecordAlreadyExistException;

	public Movie removeMovie(int movieid) throws RecordNotFoundException;
	
	public Movie updateMovie(Movie movie) throws RecordNotFoundException;
	
	public Movie addMovieToShow(Movie movie, Integer showId) throws RecordNotFoundException;

	public Movie viewMovie(int movieid) throws RecordNotFoundException;

	public List<Movie> viewMovieList() throws RecordNotFoundException;

	public List<Movie> viewMovieList(int theatreid);

	public List<Movie> viewMovieList(LocalDate date);
}
