package com.capgemini.MovieTicket.Repository;

import com.capgemini.MovieTicket.Model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    //@Query("select s from Show s where s.theatre.theatreId = :id")
    //	List<Show> getAllByTheatreId(@Param("id") int id);
}
