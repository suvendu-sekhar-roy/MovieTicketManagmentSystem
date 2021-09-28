package com.capgemini.MovieTicket.Controller;

import java.util.List;

import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Screen;
import com.capgemini.MovieTicket.Model.Theatre;
import com.capgemini.MovieTicket.Service.ScreenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/screens")
public class ScreenController {

	Logger logger = LoggerFactory.getLogger(ScreenController.class);

	@Autowired
	private ScreenService screenService;

	@PostMapping("/addScreen")
	public ResponseEntity<Screen> addAScreen(@RequestBody Screen screen,
											 @RequestParam(required = false) Integer theatreId)
			throws RecordNotFoundException {

		logger.info("-------Screen Successfully added into Theatre " + theatreId + "---------");
		return ResponseEntity.ok(screenService.addScreen(screen, theatreId));
	}

	@PutMapping("/updateScreen")
	public ResponseEntity<Screen> updateScreen(@RequestBody Screen s, @RequestParam(required = false) Integer theatreId)
			throws  RecordNotFoundException {

		ResponseEntity<Screen> response = null;
		if (s == null) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			s = screenService.updateScreen(s, theatreId);
			response = new ResponseEntity<>(s, HttpStatus.OK);
			logger.info("-------Sceen Updated Successfully---------");
		}
		return response;
	}

	@GetMapping("/showAll")
	public ResponseEntity<List<Screen>> viewScreenList() throws  RecordNotFoundException {

		logger.info("-------List Of Screens Fetched Successfully---------");
		return ResponseEntity.ok(screenService.viewScreenList());
	}
	
	@GetMapping("/viewTheatre/{screenId}")
	public ResponseEntity<Theatre>  getTheatreById(@PathVariable int screenId) throws RecordNotFoundException {
		ResponseEntity<Theatre> response = null;
		try {
			Theatre theatre = screenService.getTheatre(screenId);
			response = new ResponseEntity<>(theatre, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@GetMapping("/viewScreenById/{screenId}")
	public ResponseEntity<Screen> viewScreen(@PathVariable int screenId)
			throws RecordNotFoundException {
		ResponseEntity<Screen> response = null;
		try {
			Screen screen = screenService.viewScreen(screenId);
			response = new ResponseEntity<>(screen, HttpStatus.OK);
			logger.info("-------Screen Found---------");
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new RecordNotFoundException("Screen dosen't exist");
		}
		return response;
	}

}
