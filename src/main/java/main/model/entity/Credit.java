package main.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Credit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "credit_limit", nullable = false, columnDefinition = "DOUBLE")
    private double creditLimit;

    @Column(name = "interest_rate", nullable = false, columnDefinition = "DOUBLE")
    private double interestRate;

    @OneToMany(mappedBy = "credit")
    private Set<CreditOffer> creditOffers;
}
