package dev.nicklatcham.shopthing.repairOrder;

import dev.nicklatcham.shopthing.exception.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@RestControllerAdvice
public class RepairOrderControllerAdvice {

  public static final Logger LOGGER = LoggerFactory.getLogger(RepairOrderControllerAdvice.class);

  @ResponseBody
  @ExceptionHandler(RepairOrderNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ErrorMessage customerNotFoundHandler(final RepairOrderNotFoundException ex, WebRequest req) {
    final ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(),
        ex.getLocalizedMessage(), req.getDescription(false));
    LOGGER.info(errorMessage.toString());
    return errorMessage;
  }

}