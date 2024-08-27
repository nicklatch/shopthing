package dev.nicklatcham.shopthing.customer;

import java.util.List;

import dev.nicklatcham.shopthing.vehicle.Vehicle;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  private String name;

  // FIXME: Does not allow for country code
  @Pattern(regexp = "(^$|[0-9]{10})")
  private String phone;

  @Email
  private String email;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<Vehicle> assets;

  Customer() {
  }

  public Customer(String name, String phone, String email) {
    this.name = name;
    this.phone = phone;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Vehicle> getAssets() {
    return assets;
  }

}