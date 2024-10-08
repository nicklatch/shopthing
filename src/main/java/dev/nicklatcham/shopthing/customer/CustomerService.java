package dev.nicklatcham.shopthing.customer;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final CustomerRepository customerRepo;

  public CustomerService(CustomerRepository customerRepo) {
    this.customerRepo = customerRepo;
  }

  public List<Customer> getAllCustomers() {
    return customerRepo.findAll();
  }

  public Customer getCustomerById(Long id) {
    return customerRepo
        .findById(id)
        .orElseThrow(() -> new CustomerNotFoundException(id));
  }

  public Customer createCustomer(Customer customer) {
    return customerRepo.save(customer);
  }

  public Boolean isValidId(Long id) {
    return customerRepo.existsById(id);
  }

  public Customer update(Customer updatedCustomer) {
    Customer persistedUpdatedCustomer = this.getCustomerById(updatedCustomer.getId());

    persistedUpdatedCustomer.setName(updatedCustomer.getName());
    persistedUpdatedCustomer.setEmail(updatedCustomer.getEmail());
    persistedUpdatedCustomer.setPhone(updatedCustomer.getPhone());

    return customerRepo.save(persistedUpdatedCustomer);
  }

}