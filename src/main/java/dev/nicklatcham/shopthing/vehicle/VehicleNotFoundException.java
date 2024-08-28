package dev.nicklatcham.shopthing.vehicle;

public class VehicleNotFoundException extends RuntimeException {

  public VehicleNotFoundException() {
    super("Vehicle not found");
  }

  public VehicleNotFoundException(Long id) {
    super("Vehicle not found with id=" + id);
  }
}