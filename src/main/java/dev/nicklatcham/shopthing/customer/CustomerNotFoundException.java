package dev.nicklatcham.shopthing.customer;

/**
 * VehicleNotFoundException
 */
public class CustomerNotFoundException extends RuntimeException {

  public CustomerNotFoundException() {
    super("Customer not found");
  }

  public CustomerNotFoundException(Long id) {
    super("Customer not found with id=" + id);
  }
}