package dev.nicklatcham.shopthing.database;

import dev.nicklatcham.shopthing.repairOrder.RepairOrder;
import dev.nicklatcham.shopthing.repairOrder.RepairOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.nicklatcham.shopthing.customer.Customer;
import dev.nicklatcham.shopthing.customer.CustomerRepository;
import dev.nicklatcham.shopthing.vehicle.Vehicle;
import dev.nicklatcham.shopthing.vehicle.VehicleRepository;

@Configuration
public class DatabaseSeeder {

  private static final Logger log = LoggerFactory.getLogger(DatabaseSeeder.class);

  @Bean
  CommandLineRunner initDatabase(VehicleRepository vRepo, CustomerRepository cRepo,
      RepairOrderRepository roRepo) {
    // TODO: Set up faker to generate data and persist with batching.
    return args -> {
      Customer newCust1 = cRepo.save(
          new Customer("ACME Trucking", "6519996768", "breakdowns@acmetrucking.com"));
      Customer newCust2 = cRepo.save(
          new Customer("Other Lines", "7155550978", "info@otherlines.com"));
      Vehicle newVeh1 = vRepo.save(
          new Vehicle("Kenworth", "T680", "JKAKLMG181A024994", 734_345, 12_00, newCust1));
      Vehicle newVeh2 = vRepo.save(
          new Vehicle("Kenworth", "T680", "1KAKLMG181HJ35698", 987_102, 13_099, newCust2));

      log.info("Seeding: {}", newCust1);
      log.info("Seeding: {}", newCust2);
      log.info("Seeding: {}", newVeh1);
      log.info("Seeding: {}", newVeh2);

      log.info("Seeding: {}", roRepo.save(new RepairOrder("Some notes", newCust1, newVeh1)));
      log.info("Seeding: {}", roRepo.save(new RepairOrder("Some notes", newCust2, newVeh2)));
    };
  }
}