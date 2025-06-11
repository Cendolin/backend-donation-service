package id.hanifu.cendolin_donation_service.controllers;

import id.hanifu.cendolin_donation_service.dtos.DonationDto;
import id.hanifu.cendolin_donation_service.entities.DonationEntity;
import id.hanifu.cendolin_donation_service.services.DonationService;
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

    @Autowired
    public DonationController(DonationService donationService, UserService userService) {
        this.donationService = donationService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String donationIndex() {
        return "Hello World";
    }

    @PostMapping(value = "/{userId}")
    public ResponseEntity<DonationEntity> createDonation(
            @PathVariable(name = "userId") String userId,
            @RequestBody @Valid DonationDto donationDto
    ) {
        if (!this.userService.exists(userId)) {
            return ResponseEntity.notFound().build();
        }

        Optional<DonationEntity> donationEntity = this.donationService.create(userId, donationDto);
        return donationEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.internalServerError().build());
    }
}
