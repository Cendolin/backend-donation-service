package id.hanifu.cendolin_donation_service.controllers;

import id.hanifu.cendolin_donation_service.dtos.DonationDto;
import id.hanifu.cendolin_donation_service.entities.DonationEntity;
import id.hanifu.cendolin_donation_service.services.DonationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DonationController {
    private final DonationService donationService;

    @Autowired
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String donationIndex() {
        return "Hello World";
    }

    @PostMapping(value = "/:{userId}")
    public ResponseEntity<DonationEntity> createDonation(
            @PathVariable(name = "userId") String userId,
            @RequestBody @Valid DonationDto donationDto
    ) {
        Optional<DonationEntity> donationEntity = this.donationService.create(userId, donationDto);
        return donationEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.internalServerError().build());
    }
}
