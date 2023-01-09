package main;

import main.model.entity.Credit;
import main.model.entity.Customer;
import main.model.repository.CreditRepository;
import main.model.repository.CustomerRepository;
import main.model.service.impl.CustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = {"main.model.entity"})
public class Main {
    CustomerImpl customer;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    CreditRepository creditRepository;

    @Autowired
    CustomerRepository customerRepository;


    @Bean
    public CommandLineRunner createDemoDataIfNeeded() {
        return args -> {
            if (creditRepository.count() == 0) {
                List<Credit> credits = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Credit credit = new Credit();
                    credit.setCreditLimit(Math.round(10000000.00 * Math.random()));
                    credit.setInterestRate(Math.round(36.00 * Math.random() * 100.00) / 100.00);
                    credits.add(credit);
                }
                creditRepository.saveAll(credits);
            }
            if (customerRepository.count() == 0) {
                List<Customer> customers = new ArrayList<>();
                ArrayList<String> names = ReadDataFile.readAllDataAtOnce("src/test/testResources/Customers.csv");
                ArrayList<String> emails = ReadDataFile.readAllDataAtOnce("src/test/testResources/Emails");
                ArrayList<String> telNumbers = ReadDataFile.readAllDataAtOnce("src/test/testResources/TelNumbers");
                for (int i = 0; i < 20; i++) {
                    Customer customer = new Customer();
                    customer.setPassportNumber(Math.round(9000000000.00 * Math.random() + 1000000000));
                    customer.setEmail(emails.get(i).toString());
                    customer.setPhoneNumber(telNumbers.get(i).toString());
                    customer.setFullName(String.valueOf(names.get(i)));
                    customers.add(customer);
                }
                customerRepository.saveAll(customers);
            }
        };
    }
}