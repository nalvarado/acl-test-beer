package com.proyect.beer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proyect.beer.dto.BeerDTO;
import com.proyect.beer.model.BeerModel;
import com.proyect.beer.repository.BeerRepositoy;

@Component
public class BeerServiceImpl implements BeerService {

	@Autowired
	private BeerRepositoy beerRepositoy;
	
	
	public List<BeerDTO> getBeer(){
		
		List<BeerDTO> response = new ArrayList<BeerDTO>();
		List<BeerModel> beerModel =  beerRepositoy.findAll();
		
		for(BeerModel beer : beerModel) {
			BeerDTO dto = new BeerDTO();
			dto.setId(beer.getId());
			dto.setName(beer.getName());
			response.add(dto);
		}
		return response;
	}
	
}
