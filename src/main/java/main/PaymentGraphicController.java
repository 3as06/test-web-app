package main;

import main.model.service.impl.CreditOfferImpl;
import main.model.service.impl.PaymentGraphicImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@EntityScan("main.entity")
public class PaymentGraphicController {
    @Autowired
    PaymentGraphicImpl paymentGraphicImpl;
    @Autowired
    CreditOfferImpl creditOfferImpl;

    @GetMapping("/payment-graphic/{id}")
    public String showCreditList(Model model, @PathVariable("id") UUID creditOfferId) {
        model.addAttribute("creditOffer", creditOfferImpl.findCreditOfferById(creditOfferId));
        model.addAttribute("paymentGraphics", paymentGraphicImpl.getAllGraphicsForCreditOffer(creditOfferId));
        model.addAttribute("creditFullPrice", paymentGraphicImpl.creditFullPrice(creditOfferId));
        return "/payment-graphic";
    }
}
