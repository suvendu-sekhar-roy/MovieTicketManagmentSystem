package com.capgemini.MovieTicket.Controller;

import com.capgemini.MovieTicket.Exception.RecordNotFoundException;
import com.capgemini.MovieTicket.Model.Show;
import com.capgemini.MovieTicket.Service.ShowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;
    Logger log = LoggerFactory.getLogger(ShowController.class);
    /*------------ADD A NEW SHOW----------*/
    @PostMapping("/addshow")
    public ResponseEntity<Show> addShow(@RequestBody Show show, @RequestParam(required = false) Integer theatreId,
                                        @RequestParam(required = false) Integer screenId) {
        showService.addShow(show,theatreId,screenId );
        log.info("-------Show Added Successfully--------");
        return new ResponseEntity<Show>(show, HttpStatus.CREATED);
    }

    /*--------UPDATE A SHOW--------*/    //uncomment showserviceimpl to get execute
    @PutMapping("/update")
    public ResponseEntity<Show> updateShow(@RequestBody Show show, @RequestParam(required = false) Integer theatreId,
                                           @RequestParam(required = false) Integer screenId)  {

        ResponseEntity<Show> response = null;
        if (show == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            showService.updateShow(show, theatreId, screenId);
            response = new ResponseEntity<>(show, HttpStatus.OK);
           log.info("-------Show Updated Successfully---------");
        }
        return response;
    }

    /*----------VIEW A SHOW BY ID-----------*/
    @GetMapping("/viewbyid/{showId}")
    public ResponseEntity<Show> viewShow(@PathVariable int showId)
            throws RecordNotFoundException {

        ResponseEntity<Show> response = null;
        try {
            Show show = showService.viewShow(showId);
            response = new ResponseEntity<>(show, HttpStatus.OK);
            log.info("-------Show with ShowId " + showId + " Found Successfully---------");
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new RecordNotFoundException("Show with " + showId + " id dosen't exist");
        }
        return response;
    }

    /*------DELETE SHOW BY ID---------*/
    @DeleteMapping("/deletebyid/{showID}")
    public Show deleteShow(@PathVariable int showId) {
        log.info("---------Show with id: %d " + showId + " deleted-----------");
        return showService.removeShow(showId);
    }

    /*-------VIEW SHOW LIST BY DATE--------*/
    @GetMapping("/viewbydate/{showDate}")
    public List<Show> viewShowList(@PathVariable(name = "showDate")
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
       log.info("-------Date wise shows list----------");
        return showService.viewShowList(date);
    }

    /*-------VIEW SHOW LIST BY THEATRE ID--------*/

    @GetMapping("/viewbytheatreid/{theatreid}")
    public List<Show> viewShowList(@PathVariable int theatreid) {
        log.info("---------------Show by theatreid -----------------");
        return showService.viewShowList(theatreid);
    }

    /*------VIEW ALL SHOWS--------*/
    @GetMapping("/viewall")
    public List<Show> getAllShows() {
        log.info("--------Here are all shows---------");
        return showService.viewAllShows();
    }
}
