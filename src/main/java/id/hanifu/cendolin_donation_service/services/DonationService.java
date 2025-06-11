package id.hanifu.cendolin_donation_service.services;

import id.hanifu.cendolin_donation_service.dtos.DonationDto;
import id.hanifu.cendolin_donation_service.entities.DonationEntity;
import id.hanifu.cendolin_donation_service.entities.TransactionEntity;
import id.hanifu.cendolin_donation_service.entities.UserEntity;
import id.hanifu.cendolin_donation_service.repositories.DonationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DonationService {
    private final DonationRepository donationRepository;
    private final UserService userService;
    private final PaymentService paymentService;

    @Autowired
    public DonationService(DonationRepository donationRepository, UserService userService, PaymentService paymentService) {
        this.donationRepository = donationRepository;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    public Optional<DonationEntity> create(String userId, DonationDto donationDto) {
        Optional<UserEntity> user = this.userService.get(userId);
        if (user.isEmpty()) {
            return Optional.empty();
        }

        Optional<String> donatorPhone = donationDto.getDonatorPhone();
        if (donatorPhone == null) {
            donatorPhone = Optional.of(String.valueOf("-"));
        }

        UserEntity userEntity = user.get();
        DonationEntity donation = new DonationEntity();

        donation.setInvoiceId(UUID.randomUUID().toString());
        donation.setDonatorEmail(donationDto.getDonatorEmail());
        donation.setDonatorPhone(donatorPhone.orElse("-"));
        donation.setCurrency(donationDto.getCurrency());
        donation.setMessage(donationDto.getMessage());
        donation.setPaymentType(donationDto.getPaymentMethod());
        donation.setPrice(donationDto.getAmount());
        donation.setUserEntity(userEntity);

        Optional<TransactionEntity> transactionEntity = this.paymentService.pay(donation);
        if (transactionEntity.isEmpty()) {
            return Optional.empty();
        }
        donation.setTransactionEntity(transactionEntity.get());
        donation = this.donationRepository.save(donation);

        return Optional.of(donation);
    }
}
