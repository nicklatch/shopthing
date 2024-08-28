package dev.nicklatcham.shopthing.vehicle;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.nicklatcham.shopthing.customer.Customer;
import dev.nicklatcham.shopthing.customer.CustomerNotFoundException;
import dev.nicklatcham.shopthing.customer.CustomerService;

@Service
public class VehicleService {

  private final VehicleRepository vehicleRepo;
  private final CustomerService customerService;

  public VehicleService(VehicleRepository vehicleRepo, CustomerService customerService) {
    this.vehicleRepo = vehicleRepo;
    this.customerService = customerService;
  }

  public List<Vehicle> getAllVehicles() {
    return vehicleRepo.findAll();
  }

  public Vehicle getVehicleById(Long id) {
    return vehicleRepo
        .findById(id)
        .orElseThrow(() -> new VehicleNotFoundException(id));
  }

  public Vehicle createVehicle(Vehicle newVehicle) {
    return vehicleRepo.save(newVehicle);
  }

  public Vehicle updateVehicleOwner(Long id, Long customerId) {
    Customer customer = customerService.getCustomerById(customerId);
    Vehicle vehicleToUpdate = vehicleRepo.getReferenceById(id);

    vehicleRepo.getReferenceById(id).setCustomer(customer);

    return vehicleRepo.save(vehicleToUpdate);
  }

}