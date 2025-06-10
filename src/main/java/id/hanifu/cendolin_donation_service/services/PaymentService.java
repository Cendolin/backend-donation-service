package id.hanifu.cendolin_donation_service.services;

import com.xendit.exception.XenditException;
import id.hanifu.cendolin_donation_service.entities.DonationEntity;
import id.hanifu.cendolin_donation_service.entities.TransactionEntity;
import id.hanifu.cendolin_donation_service.payments.XenditPayment;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log
public class PaymentService {
    private final XenditPayment xenditPayment;

    @Autowired
    public PaymentService(XenditPayment xenditPayment) {
        this.xenditPayment = xenditPayment;
    }

    public Optional<TransactionEntity> pay(DonationEntity donationEntity) {
        try {
            return Optional.ofNullable(this.xenditPayment.createPaymentFromDonationEntity(donationEntity));
        } catch (XenditException e) {
            log.info(e.getMessage());
            return Optional.empty();
        }
    }
}
