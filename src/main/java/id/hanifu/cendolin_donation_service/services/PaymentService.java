package id.hanifu.cendolin_donation_service.services;

import com.xendit.exception.XenditException;
import id.hanifu.cendolin_donation_service.entities.DonationEntity;
import id.hanifu.cendolin_donation_service.entities.TransactionEntity;
import id.hanifu.cendolin_donation_service.payments.Payment;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log
public class PaymentService {
    private final Payment paymentGateway;

    @Autowired
    public PaymentService(Payment paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Optional<TransactionEntity> pay(DonationEntity donationEntity) {
        try {
            return paymentGateway.pay(donationEntity);
        } catch (XenditException e) {
            log.warning("Payment failed: " + e.getMessage());
            return Optional.empty();
        }
    }
}
