package main.model.service;

import main.model.entity.Credit;

import java.util.List;
import java.util.UUID;

public interface CreditService {
    void setCredit(float creditLimit, float interestRate);

    List<Credit> findAllCredits();

    void deleteCredit(UUID uuid);

    Credit findCreditById(UUID uuid);

    void editCredit(UUID uuid, Float creditLimit, Float interestRate);
}
