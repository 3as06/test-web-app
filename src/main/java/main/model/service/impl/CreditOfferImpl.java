package main.model.service.impl;

import main.model.entity.Credit;
import main.model.entity.CreditOffer;
import main.model.entity.Customer;
import main.model.repository.CreditOfferRepository;
import main.model.repository.CreditRepository;
import main.model.repository.CustomerRepository;
import main.model.service.CreditOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreditOfferImpl implements CreditOfferService {
    @Autowired
    CreditOfferRepository creditOfferRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CreditRepository creditRepository;

    @Override
    public UUID setNewCreditOffer(Credit credit, Customer customer, int loanTerm, float loanSum) {
        CreditOffer creditOffer = new CreditOffer();
        creditOffer.setCredit(credit);
        creditOffer.setCustomer(customer);
        creditOffer.setLoanTerm(loanTerm);
        creditOffer.setLoanSum(loanSum);
        creditOfferRepository.save(creditOffer);
        return creditOffer.getId();
    }

    @Override
    public CreditOffer findCreditOfferById(UUID creditOfferId) {
        return creditOfferRepository.getOne(creditOfferId);
    }
}
