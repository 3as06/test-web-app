package main;

import main.model.entity.Credit;
import main.model.entity.Customer;
import main.model.service.impl.CreditImpl;
import main.model.service.impl.CreditOfferImpl;
import main.model.service.impl.CustomerImpl;
import main.model.service.impl.PaymentGraphicImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.UUID;

@Controller
@EntityScan("main.entity")
public class CreditOfferController {
    @Autowired
    CreditOfferImpl creditOfferImpl;

    @Autowired
    CreditImpl creditImpl;

    @Autowired
    CustomerImpl customerImpl;

    @Autowired
    PaymentGraphicImpl paymentGraphicImpl;

    @GetMapping("/credit-offer/{id}")
    public String showCreditList(Model model, @PathVariable("id") UUID customerUuid) {
        Customer customer = customerImpl.findCustomer(customerUuid);
        model.addAttribute("creditOffers", customerImpl.getOfferedCredits(customer));
        model.addAttribute("credits", creditImpl.findAllCredits());
        model.addAttribute("customer", customer);
        return "/credit-offer";
    }

    @PostMapping("/credit-offer")
    public String editCreditOffer(String customerId, String id, int loanTerm, float loanSum, Model model) {
        UUID creditUuid = UUID.fromString(id);
        UUID customerUuid = UUID.fromString(customerId);
        Credit credit = creditImpl.findCreditById(creditUuid);
        Customer customer1 = customerImpl.findCustomer(customerUuid);
        UUID creditOfferId = creditOfferImpl.setNewCreditOffer(credit, customer1, loanTerm, loanSum);
        float interestRate = credit.getInterestRate();
        paymentGraphicImpl.setPaymentGraphic(creditOfferId, interestRate, loanSum, loanTerm);
        return "redirect:/credit-offer/" + customerId;
    }
}