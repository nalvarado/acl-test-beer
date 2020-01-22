package com.proyect.beer.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.beer.dto.BeerDTO;
import com.proyect.beer.util.ExceptionUtil;

@RestController
public class beerController {
	 private static final Logger logger = LoggerFactory.getLogger(beerController.class);
	    
	
	
	@GetMapping("/beer")
	public ResponseEntity<?> getLista() {
    	logger.info("INICIO");
        try {
        	
        	BeerDTO dot = new BeerDTO();
        	
            List<BeerDTO> lista = new ArrayList<BeerDTO>();
            
            
            
            if (lista == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }catch (Exception ex){
            logger.error("EXCEPTION", ex);
            return new ResponseEntity<>(
                ExceptionUtil.getResponse(ex,null), 
                ExceptionUtil.getExcepcion(ex,null).getHttpStatus());
        }
    }
	
}
