package com.capgemini.MovieTicket.Repository;

import com.capgemini.MovieTicket.Model.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer> {

}
