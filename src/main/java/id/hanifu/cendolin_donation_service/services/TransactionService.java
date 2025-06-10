package id.hanifu.cendolin_donation_service.services;

import id.hanifu.cendolin_donation_service.dtos.TransactionDto;
import id.hanifu.cendolin_donation_service.entities.DonationEntity;
import id.hanifu.cendolin_donation_service.entities.TransactionEntity;
import id.hanifu.cendolin_donation_service.enums.PaymentStatus;
import id.hanifu.cendolin_donation_service.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionEntity create(TransactionDto transactionDto, DonationEntity donationEntity) {
        String transactionId = UUID.randomUUID().toString();
        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setTransactionId(transactionId);
        transactionEntity.setAmount(transactionDto.getPrice());
        transactionEntity.setCustomerName(transactionDto.getCustomerName());
        transactionEntity.setPaymentStatus(PaymentStatus.WAITING);
        transactionEntity.setCustomerEmail(transactionDto.getCustomerEmail());
        transactionEntity.setCustomerPhone(transactionDto.getCustomerPhone().orElse(""));
        transactionEntity.setQrisString(transactionDto.getQrisString().orElse(""));
        transactionEntity.setPaymentGatewayUrl(transactionDto.getPaymentGatewayUrl().orElse(""));
        transactionEntity.setDonationEntity(donationEntity);

        return this.transactionRepository.save(transactionEntity);
    }
}
