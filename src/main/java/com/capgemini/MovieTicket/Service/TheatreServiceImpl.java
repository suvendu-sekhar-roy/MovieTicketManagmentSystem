package com.capgemini.MovieTicket.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.capgemini.MovieTicket.Exception.RecordAlreadyExistException;
import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Booking;
import com.capgemini.MovieTicket.Model.Movie;
import com.capgemini.MovieTicket.Model.Show;
import com.capgemini.MovieTicket.Model.Theatre;
import com.capgemini.MovieTicket.Repository.MovieRepository;
import com.capgemini.MovieTicket.Repository.ScreenRepository;
import com.capgemini.MovieTicket.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepository theatrerepository;

	@Autowired
	ScreenRepository screenRepository;
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Theatre> getAllTheatres() throws RecordNotFoundException {
		List<Theatre> the = (List<Theatre>) theatrerepository.findAll();
		//if (the.size() == 0) throw new TheatreNotFoundException("No theatres found");
		return the;
	}

	@Override
	public Theatre findTheatres(int theatreId) {
		// TODO Auto-generated method stub
		if (theatrerepository.findById(theatreId).isPresent()) {
			return theatrerepository.findById(theatreId).get();
		} else
			return null;
	}

	@Override
	public Theatre addTheatre(Theatre m) throws RecordAlreadyExistException {
		if (m != null) {
			if (theatrerepository.existsById(m.getTheatreId())) {
				throw new RecordAlreadyExistException("Theatre already exists");
			} else {
				theatrerepository.save(m);
				//screenRepository.save(m.getScreen());
			}
		}
		return m;
	}

	@Override
	public Theatre updateTheatre(Theatre m) throws RecordNotFoundException {
		// TODO Auto-generated method stub

			if (theatrerepository.existsById(m.getTheatreId())) {
				theatrerepository.save(m);
			} else {
				throw new RecordNotFoundException("Theatre doesn't exists");
			}
		return m;
	}

	@Override
	public String deleteTheatreById(Integer theatreId) throws RecordNotFoundException {
		Optional<Theatre> findTheatreById = theatrerepository.findById(theatreId);

		if (findTheatreById.isPresent()) {
			theatrerepository.deleteById(theatreId);
			return "Booking Canceled!!";
		} else {
			throw new RecordNotFoundException("Booking with Ticket Id: " + theatreId + " not exists!!");

		}
	}


	@Override
	public List<Theatre> findTheatresByMovie(Integer movieId) throws RecordNotFoundException {
		List<Theatre> theatreList=new ArrayList<>();
		Movie movie=movieRepository.findById(movieId).get();
		Integer showwID=movie.getShow().getShowId();
		List<Theatre> theatres = theatrerepository.findAll();
		for(Theatre theatre:theatres) {
			List<Show> shows =theatre.getShow();
			for(Show show:shows){
				if(show.getShowId()==showwID) {
					theatreList.add(theatre);
				}
			}
		}
		return theatreList;
	}
}
