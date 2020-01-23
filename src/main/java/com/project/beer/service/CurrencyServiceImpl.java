package com.project.beer.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonParser;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);
	@Value("${service.currency}")
	private String urlServicio;
	
	@Value("${service.currency.key}")
	private String key;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	public BigDecimal getCurrency(String currency) {
		logger.info(" currency: {}", currency);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlServicio)
                .queryParam("access_key", key)
                .queryParam("currencies", currency);
        return requestExchangeRate(builder);
	}
	
	 private BigDecimal requestExchangeRate(UriComponentsBuilder builder) {
		 HttpHeaders headers = new HttpHeaders();
         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
         HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		 logger.info("url->"+builder.toUriString());
		 
		 ResponseEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				 entity, String.class);
		 

	        return new JsonParser()
	                .parse(responseEntity.getBody()).getAsJsonObject()
	                .get("quotes").getAsJsonObject()
	                .entrySet().iterator().next()
	                .getValue().getAsBigDecimal();
	    }

}
