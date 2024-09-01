package dev.nicklatcham.shopthing.repairOrder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.nicklatcham.shopthing.customer.Customer;
import dev.nicklatcham.shopthing.vehicle.Vehicle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import java.sql.Date;
import java.util.Objects;

@Entity
public class RepairOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String notes;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  @JsonIgnoreProperties(value = {"customer_id"}, allowSetters = true)
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "vehicle_id", nullable = false)
  @JsonIgnoreProperties(value = {"vehicle_id"}, allowSetters = true)
  private Vehicle vehicle;

  @CreatedDate
  private Date createdAt;

  @UpdateTimestamp
  private Date updatedAt;

  protected RepairOrder() {
  }

  public RepairOrder(String notes, Customer customer, Vehicle vehicle) {
    this.notes = notes;
    this.customer = customer;
    this.vehicle = vehicle;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public Long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    RepairOrder that = (RepairOrder) o;
    return Objects.equals(id, that.id) && Objects.equals(notes, that.notes) && Objects.equals(
        customer, that.customer) && Objects.equals(vehicle, that.vehicle) && Objects.equals(
        createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(id);
    result = 31 * result + Objects.hashCode(notes);
    result = 31 * result + Objects.hashCode(customer);
    result = 31 * result + Objects.hashCode(vehicle);
    result = 31 * result + Objects.hashCode(createdAt);
    result = 31 * result + Objects.hashCode(updatedAt);
    return result;
  }

  @Override
  public String toString() {
    return STR."RepairOrder{id=\{id}, notes='\{notes}', customer=\{customer}, vehicle=\{vehicle}, createdAt=\{createdAt}, updatedAt=\{updatedAt}}";
  }
}
