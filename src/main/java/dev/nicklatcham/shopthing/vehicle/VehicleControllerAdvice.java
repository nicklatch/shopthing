package dev.nicklatcham.shopthing.vehicle;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import dev.nicklatcham.shopthing.exception.ErrorMessage;

@RestControllerAdvice
public class VehicleControllerAdvice {

  private final static Logger LOGGER = LoggerFactory.getLogger(VehicleControllerAdvice.class);

  @ResponseBody
  @ExceptionHandler(VehicleNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ErrorMessage vehicleNotFoundHandler(final VehicleNotFoundException ex, final WebRequest req) {

    final ErrorMessage errorMessage = new ErrorMessage(
        404,
        new Date(),
        ex.getLocalizedMessage(),
        req.getDescription(false));

    LOGGER.info(errorMessage.toString());

    return errorMessage;
  }

}