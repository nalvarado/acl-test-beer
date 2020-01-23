package com.project.beer.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.project.beer.exception.ErrorCreadoException;
import com.project.beer.exception.ErrorException;
import com.project.beer.exception.ErrorResponse;
import com.project.beer.exception.NotFoundException;
import com.project.beer.exception.RequestException;

public class ExceptionUtil {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

	/*
	 * Metodo que genera el Response de Error para las peticiones de servicios
	 * 
	 * @param ex Exception para ser mapeada en el responde
	 * 
	 * @return ErrorResponse
	 */
	public static ErrorResponse getResponse(Exception ex, String mensaje) {
		logger.info("Iniciando generaResponse");
		List<String> details = new ArrayList<>();
		details.add(ex.toString());
		ErrorException errorExcepcion = getExcepcion(ex, mensaje);
		ErrorResponse errorResponse = new ErrorResponse(errorExcepcion.getHttpStatus().value(),
				errorExcepcion.getMessage(), details);
		return errorResponse;
	}

	/*
	 * Metodo que genera el Exception de Error para las peticiones de servicios
	 * 
	 * @param ex Exception para ser mapeada en el responde
	 * 
	 * @return ErrorException
	 */
	public static ErrorException getExcepcion(Exception ex, String mensaje) {
		logger.info("Iniciando generaException");
		ErrorException errorEx = new ErrorException();

		if (ex instanceof ErrorCreadoException) {
			errorEx.setHttpStatus(HttpStatus.CONFLICT);
			errorEx.setMessage( ex.getMessage());

		} else if (ex instanceof RequestException) {
				errorEx.setHttpStatus(HttpStatus.BAD_REQUEST);
				errorEx.setMessage( ex.getMessage());
				
		} else if (ex instanceof NotFoundException) {
			errorEx.setHttpStatus(HttpStatus.NOT_FOUND);
			errorEx.setMessage( ex.getMessage());
		} else {
			if (mensaje != null) {
				mensaje = "[" + mensaje + " - " + ex.getMessage() + "]";
			} else {
				mensaje = "[" + ex.getMessage() + "]";
			}
			errorEx.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			errorEx.setMessage("Error en la ejecucion del servicio - " + mensaje);
		}

		return errorEx;
	}

}
