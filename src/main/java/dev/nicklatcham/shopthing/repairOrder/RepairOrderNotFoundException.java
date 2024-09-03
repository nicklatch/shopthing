package dev.nicklatcham.shopthing.repairOrder;

public class RepairOrderNotFoundException extends RuntimeException{

  public RepairOrderNotFoundException() {
    super("Repair Order not found");
  }

  public RepairOrderNotFoundException( Long id) {
    super("Repair Order not found with id=" + id);
  }
}
