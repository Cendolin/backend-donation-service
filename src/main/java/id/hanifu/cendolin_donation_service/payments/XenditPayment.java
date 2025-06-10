package id.hanifu.cendolin_donation_service.payments;

import com.xendit.XenditClient;
import com.xendit.exception.XenditException;
import com.xendit.model.QRCode;
import id.hanifu.cendolin_donation_service.dtos.TransactionDto;
import id.hanifu.cendolin_donation_service.entities.DonationEntity;
import id.hanifu.cendolin_donation_service.entities.TransactionEntity;
import id.hanifu.cendolin_donation_service.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class XenditPayment {
    @Value("${xendit_api_key}")
    private String apiKey;
    private final XenditClient xenditClient;
    private final TransactionService transactionService;

    @Autowired
    public XenditPayment(TransactionService transactionService) throws Exception {
        if (this.apiKey.length() < 10) {
            throw new Exception("API Key not valid");
        }
        this.xenditClient = new XenditClient.Builder()
                .setApikey(this.apiKey)
                .build();

        this.transactionService = transactionService;
    }

    public TransactionEntity createPaymentFromDonationEntity(DonationEntity donationEntity) throws XenditException {
        String transactionId = UUID.randomUUID().toString();
        QRCode qrCode = this.xenditClient.qrCode.createQRCode(transactionId, QRCode.QRCodeType.DYNAMIC, donationEntity.getCurrency(), donationEntity.getPrice());

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setPrice(donationEntity.getPrice());
        transactionDto.setCustomerName(donationEntity.getDonatorName());
        transactionDto.setCustomerEmail(donationEntity.getDonatorEmail());
        transactionDto.setPaymentGatewayUrl(Optional.empty());
        transactionDto.setQrisString(Optional.ofNullable(qrCode.getQrString()));

        return this.transactionService.create(transactionDto, donationEntity);
    }
}
