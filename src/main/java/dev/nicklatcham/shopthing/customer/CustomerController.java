package dev.nicklatcham.shopthing.customer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService customerService;
  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<List<Customer>> getAllCustomers() {
    return ResponseEntity.ok(customerService.getAllCustomers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    final Customer result = customerService.getCustomerById(id);
    LOGGER.debug("Result: {}", result);

    return ResponseEntity.ok(result);
  }

  @PostMapping
  public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer newCustomer) {
    final Customer created = customerService.createCustomer(newCustomer);
    LOGGER.debug("Created Customer: {}", created);

    return ResponseEntity.status(201).body(created);
  }

  @PutMapping("/{id}/edit")
  public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer updatedCustomer) {
    final Customer updated = customerService.update(updatedCustomer);
    LOGGER.debug("Updated Customer: {}", updated);

    return ResponseEntity.ok(updated);
  }

}