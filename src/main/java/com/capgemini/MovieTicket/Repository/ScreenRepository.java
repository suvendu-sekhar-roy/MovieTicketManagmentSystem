package com.capgemini.MovieTicket.Repository;

import com.capgemini.MovieTicket.Model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

 // @category ScreenRepository

public interface ScreenRepository extends JpaRepository<Screen, Integer> {

}
