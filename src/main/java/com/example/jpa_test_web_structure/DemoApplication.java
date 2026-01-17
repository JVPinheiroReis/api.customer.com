package com.example.jpa_test_web_structure;

import com.example.jpa_test_web_structure.domain.Customer;
import com.example.jpa_test_web_structure.respository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(
        DemoApplication.class
    );

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return args -> {
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            logger.info("Customers found with findAll():");
            logger.info("-------------------------------");
            repository
                .findAll()
                .forEach(customer -> {
                    logger.info(customer.toString());
                });
            logger.info("");

            Customer customer = repository.findById(1L);
            logger.info("Customer found with findById(1L):");
            logger.info("--------------------------------");
            logger.info(customer.toString());
            logger.info("");

            logger.info("Customer found with findByFirstName('Kim'):");
            logger.info("--------------------------------------------");
            repository
                .findByFirstName("Kim")
                .forEach(kim -> {
                    logger.info(kim.toString());
                });
            logger.info("");

            logger.info("Customer found with findByLastName('Bauer'):");
            logger.info("--------------------------------------------");
            repository
                .findByLastName("Bauer")
                .forEach(bauer -> {
                    logger.info(bauer.toString());
                });
            logger.info("");
        };
    }
}
