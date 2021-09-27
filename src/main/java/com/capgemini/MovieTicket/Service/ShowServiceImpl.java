package com.capgemini.MovieTicket.Service;

import com.capgemini.MovieTicket.Model.Screen;
import com.capgemini.MovieTicket.Model.Show;
import com.capgemini.MovieTicket.Model.Theatre;
import com.capgemini.MovieTicket.Repository.ScreenRepository;
import com.capgemini.MovieTicket.Repository.ShowRepository;
import com.capgemini.MovieTicket.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService{
        @Autowired
        private ShowRepository showRepository;
        @Autowired
        private TheatreRepository theatreRepository;
        @Autowired
        private ScreenRepository screenRepository;
        @Override
        public Show addShow(Show show) {
            return showRepository.save(show);
        }

        @Override
        public Show updateShow(Show show, Integer theatreId, Integer screenId) {
        Theatre theatre = new Theatre();
        Screen screen = new Screen();
        if (theatreId != null) {
            theatre = theatreRepository.getById(theatreId);
            show.setTheatre(theatre);
        }
        if (screenId != null) {
            screen = screenRepository.getById(screenId);
            show.setScreen(screen);
        }
        showRepository.save(show);
        return show;

        }

        @Override
        public Show removeShow(int showId) {
            Show s = showRepository.findById(showId).get();
            showRepository.delete(s);
            return s;
        }

        @Override
        public Show viewShow(int showId) {
            return showRepository.findById(showId).get();
        }
/*
        @Override
        public List<Show> viewShowList(int theatreid) {
            return showRepository.getAllByTheatreId(theatreid);

        }
*/
        @Override
        public List<Show> viewShowList(LocalDate date) {
            List<Show> showList = new ArrayList<>();
            for(Show show:showRepository.findAll()) {
                if (show.getShowDate() != null && show.getShowDate().isEqual(date))
                    showList.add(show);
            }

            return showList;
        }

        @Override
        public List<Show> viewAllShows() {
            return (List<Show>) showRepository.findAll();
        }
}
