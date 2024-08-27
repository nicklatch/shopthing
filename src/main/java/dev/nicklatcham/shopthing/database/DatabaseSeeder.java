package dev.nicklatcham.shopthing.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.nicklatcham.shopthing.vehicle.Vehicle;
import dev.nicklatcham.shopthing.vehicle.VehicleRepository;

/**
 * DatabaseSeeder
 */
@Configuration
public class DatabaseSeeder {
  private static final Logger log = LoggerFactory.getLogger(DatabaseSeeder.class);

  @Bean
  CommandLineRunner initDatabase(VehicleRepository repo) {
    return args -> {
      log.info("Seeding: " + repo.save(new Vehicle("Kenworth", "T680", "JKAKLMG181A024994")));
      log.info("Seeding: " + repo.save(new Vehicle("Kenworth", "T680", "1KAKLMG181HJ35698")));
    };
  }
}