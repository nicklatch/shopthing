package dev.nicklatcham.shopthing.exception;

import java.util.Collections;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * GlobalControllerExceptionAdvice
 */
@RestControllerAdvice
public class GlobalControllerExceptionAdvice {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionAdvice.class);

  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorMessage constraintValidationExeptionHandler(final MethodArgumentNotValidException ex, final WebRequest req) {
    // TODO: Parse error message into something that is actually readable.
    final ErrorMessage errorMessage = new ErrorMessage(
        400,
        new Date(),
        ex.getMessage(),
        req.getDescription(false));

    ex.getAllErrors().forEach(e -> LOGGER.info("{}\n", e));

    LOGGER.error("An Error Occurred: {}\n\terrors: {}\n\t", ex.getMessage(),
        ex.getAllErrors().stream().map(ObjectError::toString).toList());

    // LOGGER
    // .atError()
    // .setMessage("An Error Occured")
    // .addKeyValue("message", ex.getMessage())
    // .addKeyValue("errors", ex.getAllErrors())
    // .addKeyValue("description", req.getDescription(false))
    // .log();

    return errorMessage;
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  ErrorMessage unhandledExceptionHandler(final Exception ex, final WebRequest req) {
    final ErrorMessage errorMessage = new ErrorMessage(
        500,
        new Date(),
        ex.getLocalizedMessage(),
        req.getDescription(false));

    LOGGER.error(errorMessage.toString());

    return errorMessage;
  }
}