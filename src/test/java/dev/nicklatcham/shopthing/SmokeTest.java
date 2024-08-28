package dev.nicklatcham.shopthing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.nicklatcham.shopthing.customer.CustomerController;
import dev.nicklatcham.shopthing.vehicle.VehicleController;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class SmokeTest {

  @Autowired
  private VehicleController vehicleController;

  @Autowired
  CustomerController customerController;

  @Test
  void contextLoads() throws Exception {
    assertThat(vehicleController).isNotNull();
    assertThat(customerController).isNotNull();
  }

}