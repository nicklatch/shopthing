package dev.nicklatcham.shopthing.vehicle;

import org.hibernate.validator.constraints.Length;

import dev.nicklatcham.shopthing.customer.Customer;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  @Size(min = 2, message = "Make must be at least 2 characters long")
  private String make;

  @NotBlank
  @Size(min = 1, message = "Model must be at least 1 character long")
  private String model;

  @NotBlank
  @Length(min = 17, max = 17, message = "VIN must be exactly 17 characters long")
  private String vin;

  @Nullable
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  Vehicle() {
  }

  public Vehicle(String make, String model, String vin) {
    this.make = make;
    this.model = model;
    this.vin = vin;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((make == null) ? 0 : make.hashCode());
    result = prime * result + ((model == null) ? 0 : model.hashCode());
    result = prime * result + ((vin == null) ? 0 : vin.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Vehicle other = (Vehicle) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (make == null) {
      if (other.make != null)
        return false;
    } else if (!make.equals(other.make))
      return false;
    if (model == null) {
      if (other.model != null)
        return false;
    } else if (!model.equals(other.model))
      return false;
    if (vin == null) {
      if (other.vin != null)
        return false;
    } else if (!vin.equals(other.vin))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Vehicle [id=" + id + ", make=" + make + ", model=" + model + ", vin=" + vin + "]";
  }

}