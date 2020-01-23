package com.project.beer.service;

import java.util.List;

import com.project.beer.dto.BeerDTO;
import com.project.beer.exception.ErrorCreadoException;
import com.project.beer.model.BeerModel;

public interface BeerService {

	
	List<BeerDTO> getBeer();
	
	BeerModel getBeer(Integer id);
	
	String saveBeer(BeerDTO dto) throws ErrorCreadoException;
	
}
