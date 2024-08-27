package dev.nicklatcham.shopthing.customer;

/**
 * CustomerController
 */
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }
}