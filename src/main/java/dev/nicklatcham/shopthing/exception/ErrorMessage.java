package dev.nicklatcham.shopthing.exception;

import java.util.Date;

/**
 * ErrorMessage
 */
public record ErrorMessage(int statusCode, Date timestamp, String message, String description) {
}