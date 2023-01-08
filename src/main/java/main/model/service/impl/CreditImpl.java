package main.model.service.impl;

import main.model.entity.Credit;
import main.model.repository.CreditRepository;
import main.model.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class  CreditImpl implements CreditService {
    @Autowired
    CreditRepository creditRepository;

    @Override
    public void setCredit(float creditLimit, float interestRate) {
        Credit credit = new Credit();
        credit.setCreditLimit(creditLimit);
        credit.setInterestRate(interestRate);
        creditRepository.save(credit);
    }

    public void setCredit(Credit credit) {
        creditRepository.save(credit);
    }

    @Override
    public List<Credit> findAllCredits() {
        return creditRepository.findAll();
    }

    @Override
    public void deleteCredit(UUID uuid) {
        creditRepository.deleteById(uuid);
    }

    @Override
    public Credit findCreditById(UUID uuid) {
        Credit credit = creditRepository.getOne(uuid);
        return credit;
    }

    @Override
    public void editCredit(UUID uuid, Float creditLimit, Float interestRate) {
        Credit credit = findCreditById(uuid);
        credit.setCreditLimit(creditLimit);
        credit.setInterestRate(interestRate);
        creditRepository.save(credit);
    }
}
