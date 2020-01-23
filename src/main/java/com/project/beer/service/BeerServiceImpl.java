package com.project.beer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.beer.dto.BeerDTO;
import com.project.beer.exception.ErrorCreadoException;
import com.project.beer.exception.NotFoundException;
import com.project.beer.model.BeerModel;
import com.project.beer.repository.BeerRepositoy;

@Component
public class BeerServiceImpl implements BeerService {

	@Autowired
	private BeerRepositoy beerRepositoy;

	public List<BeerDTO> getBeer() {

		List<BeerDTO> response = new ArrayList<BeerDTO>();
		List<BeerModel> beerModel = beerRepositoy.findAll();

		for (BeerModel beer : beerModel) {
			BeerDTO dto = new BeerDTO();
			dto.setId(beer.getId());
			dto.setName(beer.getName());
			dto.setCountry(beer.getCountry());
			dto.setPrice(beer.getPrice());
			dto.setCurrency(beer.getCurrency());
			dto.setBrewery(beer.getBrewery());
			response.add(dto);
		}
		return response;
	}

	@Override
	public String saveBeer(BeerDTO dto) throws ErrorCreadoException {
		if (beerRepositoy.findById(dto.getId()).isPresent())
			throw new ErrorCreadoException("El ID de la cerveza ya existe");

		BeerModel beer = new BeerModel();
		beer.setId(dto.getId());
		beer.setName(dto.getName());
		beer.setCountry(dto.getCountry());
		beer.setPrice(dto.getPrice());
		beer.setBrewery(dto.getBrewery());
		beer.setCurrency(dto.getCurrency());
		beerRepositoy.save(beer);

		return "Cerveza creada";
	}

	@Override
	public BeerModel getBeer(Integer id) {
		return beerRepositoy.findById(id).orElseThrow(() -> new NotFoundException("El Id de la cerveza no existe"));
	}

}
