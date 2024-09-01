package dev.nicklatcham.shopthing.vehicle;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

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
  public ResponseEntity<Vehicle> getVehicleById(@PathVariable final Long id) {
    final Vehicle result = vehicleService.getVehicleById(id);
    logger.debug("Got Vehicle: {}", result);

    return ResponseEntity.ok(result);
  }

  @PostMapping
  public ResponseEntity<?> createVehicle(@Valid @RequestBody Vehicle newVehicle) {
    final Vehicle created = vehicleService.createVehicle(newVehicle);
    logger.debug(STR."Created Vehicle: \{newVehicle.toString()}");
    return ResponseEntity.status(201).body(created);
  }

  @PutMapping("/{id}/update/owner/{customerId}")
  public ResponseEntity<?> updateVehicleOwner(
      @PathVariable Long id,
      @PathVariable Long customerId) {
    return ResponseEntity.ok(vehicleService.updateVehicleOwner(id, customerId));
  }
}