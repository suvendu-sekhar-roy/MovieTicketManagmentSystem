package com.capgemini.MovieTicket.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.capgemini.MovieTicket.Exception.RecordAlreadyExistException;
import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Booking;
import com.capgemini.MovieTicket.Model.Movie;
import com.capgemini.MovieTicket.Model.Theatre;
import com.capgemini.MovieTicket.Repository.MovieRepository;
import com.capgemini.MovieTicket.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepository theatrerepository;
	/*
	@Autowired
	ScreenRepository screenRepository;
	@Autowired
	private MovieRepository moviesrepository;*/

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
			}
		}
		return m;
	}

	@Override
	public Theatre updateTheatre(Theatre m) throws RecordNotFoundException {
		// TODO Auto-generated method stub
		if (m != null) {
			if (theatrerepository.existsById(m.getTheatreId())) {
				theatrerepository.save(m);
			} else {
				throw new RecordNotFoundException("Theatre doesn't exists");
			}

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
/*
	@Override
	public List<Theatre> deleteTheatreById(int theatreId) {
		// TODO Auto-generated method stub
		theatrerepository.deleteById(theatreId);
		return theatrerepository.findAll();
	}*/


	@Override
	public List<Theatre> findTheatresByMovie(Integer movieId) throws RecordNotFoundException {
	/*	List<Theatre> theatreList=new ArrayList<>();
		Movie movie=moviesrepository.findById(movieId).get();
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
		return theatreList;*/
		return null;
	}

	/*
	 * @Override public Theatre addTheatre(Theatre t, List<Integer> screens) { //
	 * TODO Auto-generated method stub
	 * //if(theatrerepository.existsById(m.getTheatreId())) throws new Theatre
	 * List<Screen> preScs=new ArrayList<>(); if(screens!=null) { for(int id:
	 * screens) { Screen sc=screenRepository.getOne(id); preScs.add(sc);
	 * screenRepository.saveAndFlush(sc); } } t.setScreens(preScs);
	 * theatrerepository.saveAndFlush(t); return t; }
	 * 
	 * @Override public List<Theatre> updateTheatre(Theatre t, List<Integer>
	 * screenIds) { // TODO Auto-generated method stub return null; }
	 */

}
