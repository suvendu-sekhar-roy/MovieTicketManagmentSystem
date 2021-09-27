package com.capgemini.MovieTicket.Service;

import java.util.List;
import java.util.Optional;

import com.capgemini.MovieTicket.Exception.RecordAlreadyExistException;
import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Screen;
import com.capgemini.MovieTicket.Model.Theatre;
import com.capgemini.MovieTicket.Repository.ScreenRepository;
import com.capgemini.MovieTicket.Repository.TheatreRepository;
import com.capgemini.MovieTicket.Service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 
 * @author Thejesh
 * @category Screen Service Implementation
 */
@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private ScreenRepository screenRepository;
	@Autowired
	private TheatreRepository theatreRepository;

	/**
	 * @return screenList
	 */
	@Override
	public List<Screen> viewScreenList() throws RecordNotFoundException {
		List<Screen> screen = screenRepository.findAll();
		if (screen.size() == 0)
			throw new RecordNotFoundException("No screens found");
		return screen;
	}

	/**
	 * @return screen
	 */
	@Override
	public Screen addScreen(Screen screen, Integer theatreId) throws RecordAlreadyExistException {
		Theatre theatre = new Theatre();
		if (theatreId != null) {
			if (screenRepository.existsById(screen.getScreenId())) {
				throw new RecordAlreadyExistException("Screen already exists");
			} else {
				theatre = theatreRepository.getById(theatreId);
				screen.setTheatre(theatre);
			}
			screenRepository.save(screen);
		}
		return screen;
	}
	@Override
	public Screen viewScreen(int screenId) throws RecordNotFoundException {
		return screenRepository.findById(screenId).get();
		}
	/**
	 * @return updatedScreen
	 */
	@Override
	public Screen updateScreen(Screen screen, Integer theatreId) {
		Theatre theatre = new Theatre();
		if (theatreId != null) {
			theatre = theatreRepository.getById(theatreId);
			screen.setTheatre(theatre);
		}
		screenRepository.saveAndFlush(screen);
		return screen;
	}

	@Override
	public Theatre getTheatre(int screenId) throws RecordNotFoundException {
		Screen screen =screenRepository.findById(screenId).get();
		Theatre theatre=screen.getTheatre();
		return theatre;
	}

}
