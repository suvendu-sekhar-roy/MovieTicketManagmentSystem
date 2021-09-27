package com.capgemini.MovieTicket.Repository;

import com.capgemini.MovieTicket.Model.Booking;
import com.capgemini.MovieTicket.Model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
}
