package dev.nicklatcham.shopthing.customer;

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
public class CustomerControllerAdvice {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerControllerAdvice.class);

  @ResponseBody
  @ExceptionHandler(CustomerNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ErrorMessage customerNotFoundHandler(final CustomerNotFoundException ex, final WebRequest req) {
    final ErrorMessage errorMessage = new ErrorMessage(
        HttpStatus.NOT_FOUND.value(),
        new Date(),
        ex.getLocalizedMessage(),
        req.getDescription(false));

    LOGGER.info(errorMessage.toString());

    return errorMessage;
  }
}