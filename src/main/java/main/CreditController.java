package main;

import main.model.entity.Credit;
import main.model.service.impl.CreditImpl;
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
public class CreditController {
    @Autowired
    private CreditImpl creditImpl;

    @GetMapping("/credit")
    public String showCreditList(Model model) {
        model.addAttribute("credits", creditImpl.findAllCredits());
        return "/credit";
    }

    @GetMapping("/add-credit")
    public String getAddCredit() {
        return "/add-credit";
    }

    @PostMapping("/add-credit")
    private String addCredit(Credit credit, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "/add-credit";
        }
        creditImpl.setCredit(credit);
        return "redirect:/credit";
    }

    @GetMapping("/deleteCredit/{id}")
    public String delete(@PathVariable("id") UUID uuid) {
        creditImpl.deleteCredit(uuid);
        return "redirect:/credit";
    }

    @GetMapping("/editCredit/{id}")
    public String edit(Model model, @PathVariable("id") UUID uuid) {
        model.addAttribute("credit", creditImpl.findCreditById(uuid));
        return "/edit-credit";
    }

    @PostMapping("/editCredit")
    public String editConfirm(String id, Float creditLimit, Float interestRate) {
        UUID uuid = UUID.fromString(id);
        creditImpl.editCredit(uuid, creditLimit, interestRate);
        return "redirect:/credit";
    }
}

