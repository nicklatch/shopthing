package dev.nicklatcham.shopthing.vehicle;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
  private final VehicleService vehicleService;
  private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

  public VehicleController(final VehicleService vehicleService) {
    this.vehicleService = vehicleService;
  }

  @GetMapping
  public ResponseEntity<List<Vehicle>> getAllVehicles() {
    return ResponseEntity.ok(vehicleService.getAllVehicles());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getVehicleById(@PathVariable final Long id) {
    final Optional<Vehicle> result = vehicleService.getVehicleById(id);

    if (result.isEmpty()) {
      logger.warn("Given id {}, was not found", id);
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(result.get());
  }

  @PostMapping
  public ResponseEntity<?> createVehicle(@Valid @RequestBody Vehicle newVehicle, Errors errors) {

    // TODO: Better error handling. AOP?
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(errors.getAllErrors());
    }

    final Vehicle created = vehicleService.createVehicle(newVehicle);
    logger.info("Given Vehicle.id: " + newVehicle.toString());
    return ResponseEntity.status(201).body(created);
  }

  @PutMapping("/{id}/update/owner/{customerId}")
  public ResponseEntity<?> updateVehicleOwner(
      @PathVariable Long id,
      @PathVariable Long customerId) {
    return ResponseEntity.ok(vehicleService.updateVehicleOwner(id, customerId));
  }
}