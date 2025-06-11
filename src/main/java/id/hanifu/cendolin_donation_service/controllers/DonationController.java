package id.hanifu.cendolin_donation_service.controllers;

import id.hanifu.cendolin_donation_service.dtos.DonationDto;
import id.hanifu.cendolin_donation_service.entities.DonationEntity;
import id.hanifu.cendolin_donation_service.entities.TransactionEntity;
import id.hanifu.cendolin_donation_service.objects.ResponseObject;
import id.hanifu.cendolin_donation_service.services.DonationService;
import id.hanifu.cendolin_donation_service.services.TransactionService;
import id.hanifu.cendolin_donation_service.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DonationController {
    private final DonationService donationService;
    private final UserService userService;
    private final TransactionService transactionService;

    @Autowired
    public DonationController(DonationService donationService, UserService userService, TransactionService transactionService) {
        this.donationService = donationService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @RequestMapping("/")
    public String donationIndex() {
        return "Hello World";
    }

    @PostMapping(value = "/{userId}")
    public ResponseEntity<ResponseObject<DonationEntity>> createDonation(
            @PathVariable(name = "userId") String userId,
            @RequestBody @Valid DonationDto donationDto
    ) {
        if (!this.userService.exists(userId)) {
            return ResponseEntity.notFound().build();
        }

        Optional<DonationEntity> donationEntity = this.donationService.create(userId, donationDto);
        if (donationEntity.isPresent()) {
            DonationEntity donation = donationEntity.get();
            ResponseObject<DonationEntity> donationEntityResponseObject = new ResponseObject<>();
            donationEntityResponseObject.setData(donation);

            return ResponseEntity.ok(donationEntityResponseObject);
        }

        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/trx/{trxId}")
    public ResponseEntity<ResponseObject<TransactionEntity>> getTransaction(
            @PathVariable("trxId") String trxId
    ) {
        Optional<TransactionEntity> transactionEntity = this.transactionService.get(trxId);
        if (transactionEntity.isPresent()) {
            TransactionEntity transaction = transactionEntity.get();
            ResponseObject<TransactionEntity> transactionEntityResponseObject = new ResponseObject<>();
            transactionEntityResponseObject.setData(transaction);

            return ResponseEntity.ok().body(transactionEntityResponseObject);
        }

        return ResponseEntity.notFound().build();
    }
}
