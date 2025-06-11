package id.hanifu.cendolin_donation_service.payments;

import com.xendit.exception.XenditException;
import id.hanifu.cendolin_donation_service.entities.DonationEntity;
import id.hanifu.cendolin_donation_service.entities.TransactionEntity;

import java.util.Optional;

public interface Payment {
    Optional<TransactionEntity> pay(DonationEntity donation) throws XenditException;
}