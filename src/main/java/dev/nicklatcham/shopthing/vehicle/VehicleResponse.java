package dev.nicklatcham.shopthing.vehicle;

import java.util.List;

public record VehicleResponse(Vehicle vehicle, Boolean hasErrors, List<String> messages) {

  public VehicleResponse {
    messages = List.copyOf(messages);
  }

  public VehicleResponse(Vehicle vehicle, Boolean hasErrors) {
    this(vehicle, hasErrors, List.of());
  }
}