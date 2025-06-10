package id.hanifu.cendolin_donation_service.repositories;

import id.hanifu.cendolin_donation_service.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, BigInteger> {
}
