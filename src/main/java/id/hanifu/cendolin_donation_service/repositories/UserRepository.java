package id.hanifu.cendolin_donation_service.repositories;

import id.hanifu.cendolin_donation_service.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<UserEntity, BigInteger> {
    public Optional<UserEntity> findByUserId(String userId);
}