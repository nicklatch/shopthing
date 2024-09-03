package dev.nicklatcham.shopthing.repairOrder;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RepairOrderService {

  private final RepairOrderRepository repairOrderRepo;

  protected RepairOrderService(RepairOrderRepository repairOrderRepo) {
    this.repairOrderRepo = repairOrderRepo;
  }

  public List<RepairOrder> getAllRepairOrders() {
    return repairOrderRepo.findAll();
  }

  public RepairOrder getRepairOrderById(Long id) {
    return repairOrderRepo
        .findById(id)
        .orElseThrow(() -> new RepairOrderNotFoundException(id));
  }
}
