package main;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class PaymentCalc {
    private double scale = 100;
    private double payment;
    private double creditFullPrice;
    private ArrayList<Double> creditPercentsPaymentList = new ArrayList<>();
    private ArrayList<Double> creditBodyPaymentList = new ArrayList<>();

    //расчет ежемесячного аннуитетного платежа по кредиту
    public void monthlyPayment(float interestRate, double loanSum, int loanTerm) {
        double interestRatePerMonth = (interestRate/100)/12;
        payment = Math.round((loanSum * (interestRatePerMonth)/(1 - Math.pow((1 + interestRatePerMonth), -loanTerm))) * scale) / scale;
        creditFullPrice = payment * loanTerm;
    }
    //расчет гашения тела и процентов по кредиту помесячно
    public void creditBodyAndPercentsPaymentListCulc(float interestRate, double loanSum, double monthlyPayment, int loanTerm) {
        double interestRatePerMonth = (interestRate/100)/12;
        double remainingSum = loanSum;
        for(int i = 0; i < loanTerm; i++) {
            double creditPercentsPayment = Math.round(remainingSum * interestRatePerMonth * scale) / scale;
            creditPercentsPaymentList.add(creditPercentsPayment);
            double creditBodyPayment = Math.round((monthlyPayment - creditPercentsPayment) * scale) / scale;
            creditBodyPaymentList.add(creditBodyPayment);
            remainingSum = remainingSum - creditBodyPayment;
        }
    }
}
