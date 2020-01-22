package com.proyect.beer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyect.beer.model.BeerModel;

@Repository
public interface BeerRepositoy extends JpaRepository<BeerModel, Integer> {

}
