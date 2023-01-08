package main.model.repository;

import main.model.entity.CreditOffer;
import main.model.entity.PaymentGraphic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentGraphicRepository extends JpaRepository<PaymentGraphic, UUID> {
    @Query("Select pg FROM PaymentGraphic pg where pg.creditOffer = :creditOffer")
    List<PaymentGraphic> findByCreditOffer(@Param("creditOffer") CreditOffer creditOffer);

    @Query("Select SUM(payment) FROM PaymentGraphic where creditOffer = :creditOffer")
    double creditFullPrice(@Param("creditOffer") CreditOffer creditOffer);
}
