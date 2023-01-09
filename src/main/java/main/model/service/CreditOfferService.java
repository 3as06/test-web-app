package main.model.service;

import main.model.entity.Credit;
import main.model.entity.CreditOffer;
import main.model.entity.Customer;

import java.util.UUID;

public interface CreditOfferService {
    UUID setNewCreditOffer(Credit credit, Customer customer, int loanTerm, double loanSum);

    CreditOffer findCreditOfferById(UUID creditOfferId);
}
