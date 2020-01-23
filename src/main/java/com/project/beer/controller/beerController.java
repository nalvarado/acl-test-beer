package com.project.beer.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.beer.dto.BeerBoxDTO;
import com.project.beer.dto.BeerDTO;
import com.project.beer.dto.ResponseDTO;
import com.project.beer.exception.ErrorCreadoException;
import com.project.beer.exception.NotFoundException;
import com.project.beer.model.BeerModel;
import com.project.beer.service.BeerService;
import com.project.beer.service.CurrencyService;
import com.project.beer.util.ExceptionUtil;

@RestController
public class beerController {
	private static final Logger logger = LoggerFactory.getLogger(beerController.class);

	@Autowired
	private BeerService service;
	
	@Autowired 
	private CurrencyService currencyService;

	@RequestMapping(value="/beers",method=RequestMethod.GET)
	public ResponseEntity<?> getLista() {
		logger.info("INICIO");
		try {
			List<BeerDTO> lista = service.getBeer();

			if (lista == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			return new ResponseEntity<>(ExceptionUtil.getResponse(ex, null),
					ExceptionUtil.getExcepcion(ex, null).getHttpStatus());
		}
	}
	


	@PostMapping("/beers")
	public ResponseEntity<?> setCerveza(@RequestBody BeerDTO dto  ) {
		logger.info("INICIO");
		try {
			String resp = service.saveBeer(dto);
			ResponseDTO response = new ResponseDTO();
			response.setDescription(resp);
			
			
			return new ResponseEntity<>(response, HttpStatus.CREATED);
			
		} catch (ErrorCreadoException ex) {
			logger.error("EXCEPTION", ex);
			return new ResponseEntity<>(ExceptionUtil.getResponse(ex, null),
					ExceptionUtil.getExcepcion(ex, null).getHttpStatus());
		}
	}
	
	@GetMapping("/beers/{beerID}")
	public ResponseEntity<?> getBeer(@PathVariable(value = "beerID") Integer beerID ) {
		logger.info("INICIO");
		try {
			BeerModel lista = service.getBeer(beerID);
			
			return new ResponseEntity<>(lista, HttpStatus.OK);
			
		} catch (NotFoundException ex) {
			logger.error("EXCEPTION", ex);
			return new ResponseEntity<>(ExceptionUtil.getResponse(ex, null),
					ExceptionUtil.getExcepcion(ex, null).getHttpStatus());
		}
	}
	
	@GetMapping("/beers/{beerID}/boxprice")
	public ResponseEntity<?> getBoxPrice(@PathVariable(value = "beerID") Integer beerID ) {
		logger.info("INICIO");
		Integer defaultValue = 6;
		try {
			BeerModel lista = service.getBeer(beerID);
			BigDecimal value = currencyService.getCurrency(lista.getCurrency());
			logger.info("valor Obtenido::" + value.toPlainString());
			BeerBoxDTO box = new BeerBoxDTO();
			BigDecimal precioOriginalDolar = new BigDecimal(lista.getPrice());
			BigDecimal monedaCalulada = precioOriginalDolar.multiply(value);
			box.setPriceTotal( monedaCalulada.multiply(new BigDecimal(defaultValue)));
			
			return new ResponseEntity<>(box, HttpStatus.OK);
			
		} catch (NotFoundException ex) {
			logger.error("EXCEPTION", ex);
			return new ResponseEntity<>(ExceptionUtil.getResponse(ex, null),
					ExceptionUtil.getExcepcion(ex, null).getHttpStatus());
		}
	}
	
	
	

}
