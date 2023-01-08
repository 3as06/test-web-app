package main.model.service.impl;

import main.PaymentCalc;
import main.model.entity.CreditOffer;
import main.model.entity.PaymentGraphic;
import main.model.repository.CreditOfferRepository;
import main.model.repository.PaymentGraphicRepository;
import main.model.service.PaymentGraphicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentGraphicImpl implements PaymentGraphicService {
    @Autowired
    PaymentGraphicRepository paymentGraphicRepository;
    @Autowired
    CreditOfferRepository creditOfferRepository;

    @Override
    public void setPaymentGraphic(UUID creditOfferId, float interestRate, double loanSum, int loanTerm) {
        PaymentCalc paymentCalc = new PaymentCalc();
        CreditOffer creditOffer = creditOfferRepository.getOne(creditOfferId);
        paymentCalc.monthlyPayment(interestRate, loanSum, loanTerm);
        double monthlyPayment = paymentCalc.getPayment();
        paymentCalc.creditBodyAndPercentsPaymentListCulc(interestRate, loanSum, monthlyPayment, loanTerm);
        ArrayList<Double> creditPercentsPaymentList = paymentCalc.getCreditPercentsPaymentList();
        ArrayList<Double> creditBodyPaymentList = paymentCalc.getCreditBodyPaymentList();
        for(int i = 0; i < loanTerm; i++) {
            PaymentGraphic paymentGraphic = new PaymentGraphic();
            paymentGraphic.setPayment(monthlyPayment);
            paymentGraphic.setCreditPercentsPayment(creditPercentsPaymentList.get(i));
            paymentGraphic.setCreditBodyPayment(creditBodyPaymentList.get(i));
            paymentGraphic.setPaymentNumber(i+1);
            paymentGraphic.setCreditOffer(creditOffer);
            paymentGraphicRepository.save(paymentGraphic);
        }
    }

    @Override
    public List<PaymentGraphic> getAllGraphicsForCreditOffer(UUID creditOfferId) {
        CreditOffer creditOffer = creditOfferRepository.getOne(creditOfferId);
        return paymentGraphicRepository.findByCreditOffer(creditOffer);
    }

    @Override
    public double creditFullPrice(UUID creditOfferId) {
        CreditOffer creditOffer = creditOfferRepository.getOne(creditOfferId);
        double cfp = Math.round(paymentGraphicRepository.creditFullPrice(creditOffer) * 100.00) / 100.00;
        return cfp;
    }
}
