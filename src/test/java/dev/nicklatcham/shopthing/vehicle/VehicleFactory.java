package dev.nicklatcham.shopthing.vehicle;

import dev.nicklatcham.shopthing.customer.Customer;

public class VehicleFactory {

  public static Vehicle withoutCustomer(String make, String model, String vin) {
    return new Vehicle(make, model, vin);
  }

  public static Vehicle withCustomer(String make, String model, String vin, Customer customer) {
    return new Vehicle(make, model, vin, customer);
  }
}