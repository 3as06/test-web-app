package main.model.service;

import main.model.entity.CreditOffer;
import main.model.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    void setCustomer(String fullName, String phoneNumber, String email, Long passportNumber);

    void setCustomer(Customer customer);

    List<Customer> findAllCustomers();

    void deleteCustomer(UUID uuid);

    void editCustomer(UUID uuid, String fullname, String phoneNumber, String email, Long passportNumber);

    Customer findCustomer(UUID uuid);

    List<CreditOffer> getOfferedCredits(Customer customer);
}
