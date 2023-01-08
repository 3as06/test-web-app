package main.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @Column(columnDefinition = "UUID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "full_name", /*nullable = false,*/ columnDefinition = "CLOB")
    private String fullName;

    @Column(name = "phone_number", /*nullable = false,*/ columnDefinition = "CLOB")
    private String phoneNumber;

    @Column(name = "email", /*nullable = false,*/ columnDefinition = "CLOB")
    private String email;

    @Column(name = "passport_number", /*nullable = false,*/ columnDefinition = "BIGINT")
    private Long passportNumber;

    @OneToMany(mappedBy = "customer")
    private List<CreditOffer> creditOffers;
}
