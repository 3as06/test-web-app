package main.model.service;

import main.model.entity.PaymentGraphic;

import java.util.List;
import java.util.UUID;

public interface PaymentGraphicService {
    void setPaymentGraphic(UUID creditOfferId, double interestRate, double loanSum, int loanTerm);

    List<PaymentGraphic> getAllGraphicsForCreditOffer(UUID creditOfferId);

    double creditFullPrice(UUID creditOfferId);
}
