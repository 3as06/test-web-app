package main.model.service.impl;

import main.model.entity.CreditOffer;
import main.model.entity.Customer;
import main.model.repository.CustomerRepository;
import main.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void setCustomer(String fullName, String phoneNumber, String email, Long passportNumber) {
        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        customer.setPassportNumber(passportNumber);
        customerRepository.save(customer);
    }

    @Override
    public List<CreditOffer> getOfferedCredits(Customer customer) {
        return customer.getCreditOffers();
    }

    @Override
    public void setCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomer(UUID uuid) {
        customerRepository.deleteById(uuid);
    }

    @Override
    public void editCustomer(UUID uuid, String fullName, String phoneNumber, String email, Long passportNumber) {
        Customer customer = findCustomer(uuid);
        customer.setFullName(fullName);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        customer.setPassportNumber(passportNumber);
        customerRepository.save(customer);
    }

    @Override
    public Customer findCustomer(UUID uuid) {
        Customer customer = customerRepository.getOne(uuid);
        return customer;
    }
}
