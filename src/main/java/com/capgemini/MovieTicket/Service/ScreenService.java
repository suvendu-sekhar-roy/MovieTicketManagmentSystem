package com.capgemini.MovieTicket.Service;

import com.capgemini.MovieTicket.Exception.RecordAlreadyExistException;
import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Screen;
import com.capgemini.MovieTicket.Model.Theatre;

import java.util.List;


/**
 * 
 * @author Thejesh
 * @category Screen Service
 *
 */
public interface ScreenService {
	public Screen addScreen(Screen screen, Integer theatreId) throws RecordAlreadyExistException;
	public List<Screen> viewScreenList() throws RecordNotFoundException;
	public Screen updateScreen(Screen s, Integer theatreId);
	public Screen viewScreen(int screenId) throws RecordNotFoundException;
	public Theatre getTheatre(int screenId) throws RecordNotFoundException;
}
