package com.capgemini.MovieTicket.Service;

import com.capgemini.MovieTicket.Exception.RecordAlreadyExistException;
import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Theatre;

import java.util.List;

public interface TheatreService {
	public List<Theatre> getAllTheatres() throws RecordNotFoundException;

	public Theatre findTheatres(int theatreId);

	public Theatre addTheatre(Theatre t) throws RecordAlreadyExistException;

	public Theatre updateTheatre(Theatre t) throws  RecordNotFoundException;

	public String deleteTheatreById(Integer theatreId) throws RecordNotFoundException;
	
	public List<Theatre> findTheatresByMovie(Integer movieId) throws RecordNotFoundException;
}
