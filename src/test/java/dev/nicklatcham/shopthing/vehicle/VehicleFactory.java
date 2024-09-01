package dev.nicklatcham.shopthing.vehicle;

import dev.nicklatcham.shopthing.customer.Customer;

public class VehicleFactory {

  public static Vehicle withoutCustomer(String make, String model, String vin, int odometer,
      int engineHours) {
    return new Vehicle(make, model, vin, odometer, engineHours);
  }

  public static Vehicle withCustomer(String make, String model, String vin, int odometer,
      int engineHours, Customer customer) {
    return new Vehicle(make, model, vin, odometer, engineHours, customer);
  }
}