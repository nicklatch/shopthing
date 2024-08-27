package dev.nicklatcham.shopthing.vehicle;

/**
 * VehicleNotFoundException
 */
public class VehicleNotFoundException extends RuntimeException {
  public VehicleNotFoundException(Long id) {
    super("Could not find vehicle with id of " + id);
  }
}