package main.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentGraphic {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "payment_number", nullable = false, columnDefinition = "INT")
    private int paymentNumber;

    @Column(name = "payment", nullable = false, columnDefinition = "DOUBLE")
    private double payment;

    @Column(name = "credit_body_payment", nullable = false, columnDefinition = "DOUBLE")
    private double creditBodyPayment;

    @Column(name = "credit_percents_payment", nullable = false, columnDefinition = "DOUBLE")
    private double creditPercentsPayment;

    @ManyToOne
    @JoinColumn(name = "creditOffer_id")
    CreditOffer creditOffer;

}
