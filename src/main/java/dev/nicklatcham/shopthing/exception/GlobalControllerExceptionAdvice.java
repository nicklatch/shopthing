package dev.nicklatcham.shopthing.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
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
  @ExceptionHandler(TransactionSystemException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorMessage constraintValidationExeptionHandler(final TransactionSystemException txEx, final WebRequest req) {
    // TODO: Parse error message into something that is actually readable.
    final ErrorMessage errorMessage = new ErrorMessage(
        400,
        new Date(),
        txEx.getMessage(),
        req.getDescription(false));

    LOGGER.info(errorMessage.toString());

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