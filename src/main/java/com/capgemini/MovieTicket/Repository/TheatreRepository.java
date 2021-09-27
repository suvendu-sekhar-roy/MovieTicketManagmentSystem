package com.capgemini.MovieTicket.Repository;

import com.capgemini.MovieTicket.Model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
}
