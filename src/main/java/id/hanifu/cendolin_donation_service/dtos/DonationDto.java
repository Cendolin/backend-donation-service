package id.hanifu.cendolin_donation_service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.hanifu.cendolin_donation_service.annotations.ValidBigDecimal;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DonationDto {
    @JsonProperty("amount")
    @ValidBigDecimal(minValue = "100")
    private BigDecimal amount;

    @JsonProperty("donator_name")
    @NotBlank(message = "Donator name cannot blank")
    private String donatorName;

    @JsonProperty("donator_email")
    @NotBlank(message = "Donator email cannot blank")
    @Email(message = "Donator email must valid email")
    private String donatorEmail;

    @JsonProperty("donator_phone")
    @Nullable
    private String donatorPhone;

    @JsonProperty("currency")
    @Pattern(regexp = "^[A-Z]{2,3}$", message = "Currency must be a valid 3-letter code")
    private String currency;

    @JsonProperty("message")
    @NotBlank(message = "Message cannot blank")
    @Size(min = 3, max = 1000)
    private String message;

    @JsonProperty("payment_method")
    @NotBlank(message = "Payment Method cannot blank")
    private String paymentMethod;
}
