package dev.nicklatcham.shopthing.vehicle;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.nicklatcham.shopthing.customer.Customer;
import dev.nicklatcham.shopthing.repairOrder.RepairOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

  @PositiveOrZero
  @Column(nullable = false)
  private int odometer;

  @PositiveOrZero
  private int engineHours;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id", nullable = true)
  @JsonIgnoreProperties(value = { "customer_id", "assets" }, allowSetters = true)
  private Customer customer;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "vehicle", orphanRemoval = true)
  @JsonIgnoreProperties(value = { "vehicle" }, allowSetters = true)
  private Set<RepairOrder> repairOrders = new HashSet<>();

  @CreatedDate
  private Date createdAt;

  @UpdateTimestamp
  private Date updatedAt;

  protected Vehicle() {
  }

  public Vehicle(String make, String model, String vin, int odometer, int engineHours) {
    this.make = make;
    this.model = model;
    this.vin = vin;
    this.odometer = odometer;
    this.engineHours = engineHours;
  }

  public Vehicle(String make, String model, String vin, int odometer, int engineHours,
      Customer customer) {
    this.make = make;
    this.model = model;
    this.vin = vin;
    this.odometer = odometer;
    this.engineHours = engineHours;
    this.customer = customer;
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

  public int getOdometer() {
    return odometer;
  }

  public void setOdometer(int odometer) {
    this.odometer = odometer;
  }

  public int getEngineHours() {
    return engineHours;
  }

  public void setEngineHours(int engineHours) {
    this.engineHours = engineHours;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Vehicle vehicle = (Vehicle) o;
    return Objects.equals(id, vehicle.id) && Objects.equals(make, vehicle.make) && Objects.equals(
        model, vehicle.model) && Objects.equals(vin, vehicle.vin)
        && Objects.equals(customer,
            vehicle.customer)
        && Objects.equals(createdAt, vehicle.createdAt) && Objects.equals(
            updatedAt, vehicle.updatedAt);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(id);
    result = 31 * result + Objects.hashCode(make);
    result = 31 * result + Objects.hashCode(model);
    result = 31 * result + Objects.hashCode(vin);
    result = 31 * result + Objects.hashCode(customer);
    result = 31 * result + Objects.hashCode(createdAt);
    result = 31 * result + Objects.hashCode(updatedAt);
    return result;
  }

  @Override
  public String toString() {
    return "Vehicle [id=" + id + ", make=" + make + ", model=" + model + ", vin=" + vin + ", odometer=" + odometer
        + ", engineHours=" + engineHours + "]";
  }

}