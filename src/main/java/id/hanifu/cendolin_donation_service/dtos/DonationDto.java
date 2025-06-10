package id.hanifu.cendolin_donation_service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Currency;

import java.math.BigDecimal;

@Getter
@Setter
public class DonationDto {
    @JsonProperty("amount")
    @NotBlank(message = "Amount cannot blank")
    @Min(value = 100, message = "Amount must be greather than 100")
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
    @Currency(message = "Currency must valid", value = {"IDR"})
    private String currency;

    @JsonProperty("message")
    @NotBlank(message = "Message cannot blank")
    private String message;

    @JsonProperty("payment_method")
    @NotBlank(message = "Payment Method cannot blank")
    private String paymentMethod;
}
