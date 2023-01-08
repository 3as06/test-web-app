package main;

import main.model.entity.Customer;
import main.model.service.impl.CustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@EntityScan("main.entity")
public class CustomerController {
    @Autowired
    private CustomerImpl customerImpl;

    @GetMapping("/customer")
    public String showCustomers(Model model) {
        model.addAttribute("customers", customerImpl.findAllCustomers());
        return "/customer";
    }

    @GetMapping("/add-customer")
    public String getAddCredit() {
        return "/add-customer";
    }

    @PostMapping("/add-customer")
    public String addCustomer(Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/add-customer";
        }
        customerImpl.setCustomer(customer);
        return "redirect:/customer";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID uuid) {
        customerImpl.deleteCustomer(uuid);
        return "redirect:/customer";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") UUID uuid) {
        model.addAttribute("customer", customerImpl.findCustomer(uuid));
        return "/edit-customer";
    }

    @PostMapping("/edit")
    public String editConfirm(String id, String fullName, String phoneNumber, String email, Long passportNumber) {
        UUID uuid = UUID.fromString(id);
        customerImpl.editCustomer(uuid, fullName, phoneNumber, email, passportNumber);
        return "redirect:/customer";
    }
}
