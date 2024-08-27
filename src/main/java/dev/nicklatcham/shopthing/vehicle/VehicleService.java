package dev.nicklatcham.shopthing.vehicle;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class VehicleService {

  private final VehicleRepository vehicleRepo;

  public VehicleService(VehicleRepository vehicleRepo) {
    this.vehicleRepo = vehicleRepo;
  }

  public List<Vehicle> getAllVehicles() {
    return vehicleRepo.findAll();
  }

  public Optional<Vehicle> getVehicleById(Long id) {
    return vehicleRepo.findById(id);
  }

  public Vehicle createVehicle(Vehicle newVehicle) {
    return vehicleRepo.save(newVehicle);
  }

  // FIXME: Need to get reference to customer from id sent from route handler.
  public Vehicle updateVehicleOwner(Long id, Long customerId) {
    Vehicle vehicleToUpdate = vehicleRepo.getReferenceById(id);
    vehicleToUpdate.setCustomer(customerId);

    return vehicleRepo.save(vehicleToUpdate);
  }
}