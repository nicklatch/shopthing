package dev.nicklatcham.shopthing.repairOrder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/repair-orders")
public class RepairOrderController {

  private final RepairOrderService repairOrderService;

  public RepairOrderController(RepairOrderService repairOrderService) {
    this.repairOrderService = repairOrderService;
  }

  @GetMapping
  public ResponseEntity<List<RepairOrder>> index() {
    return ResponseEntity.ok(repairOrderService.getAllRepairOrders());
  }

  @GetMapping("/{id}")
  public ResponseEntity<RepairOrder> show(@PathVariable Long id) {
    return ResponseEntity.ok(repairOrderService.getRepairOrderById(id));
  }

}
