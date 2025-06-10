package id.hanifu.cendolin_donation_service.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
@Setter
public class TransactionDto {
    @NotBlank(message = "Customer name cannot blank")
    private String customerName;

    @NotBlank(message = "Customer email cannot blank")
    private String customerEmail;

    private Optional<String> customerPhone;

    private Optional<String> paymentGatewayUrl;
    private Optional<String> qrisString;

    @NotBlank(message = "Price cannot blank")
    private BigDecimal price;
}
