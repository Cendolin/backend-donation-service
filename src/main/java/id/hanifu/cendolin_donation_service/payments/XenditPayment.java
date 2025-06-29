package id.hanifu.cendolin_donation_service.payments;

import com.xendit.XenditClient;
import com.xendit.exception.XenditException;
import com.xendit.model.Invoice;
import com.xendit.model.QRCode;
import id.hanifu.cendolin_donation_service.dtos.TransactionDto;
import id.hanifu.cendolin_donation_service.entities.DonationEntity;
import id.hanifu.cendolin_donation_service.entities.TransactionEntity;
import id.hanifu.cendolin_donation_service.services.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class XenditPayment implements Payment {
    private final XenditClient xenditClient;
    private final TransactionService transactionService;

    public XenditPayment(
            TransactionService transactionService,
            @Value("${XENDIT_API_KEY}") String apiKey
    ) {
        if (apiKey == null || apiKey.length() < 10) {
            throw new IllegalArgumentException("API Key not valid");
        }
        this.xenditClient = new XenditClient.Builder()
                .setApikey(apiKey)
                .build();
        this.transactionService = transactionService;
    }

    @Override
    public Optional<TransactionEntity> pay(DonationEntity donationEntity) throws XenditException {
        String transactionId = UUID.randomUUID().toString();
//        QRCode qrCode = xenditClient.qrCode.createQRCode(
//                transactionId,
//                QRCode.QRCodeType.DYNAMIC,
//                donationEntity.getCurrency(),
//                donationEntity.getPrice().intValue()
//        );

        Invoice invoice = xenditClient.invoice.create(transactionId, donationEntity.getPrice().intValue(), donationEntity.getDonatorEmail(), "Invoice Donate");

        TransactionDto dto = new TransactionDto();
        dto.setPrice(donationEntity.getPrice());
        dto.setCustomerName(donationEntity.getDonatorName());
        dto.setQrisString(Optional.of("-"));
        dto.setCustomerEmail(donationEntity.getDonatorEmail());
        dto.setCustomerPhone(Optional.ofNullable(donationEntity.getDonatorPhone()));
        dto.setPaymentGatewayUrl(Optional.ofNullable(invoice.getInvoiceUrl()));
//        dto.setQrisString(Optional.ofNullable(qrCode.getQrString()));

        TransactionEntity tx = this.transactionService.create(dto, donationEntity);
        return Optional.of(tx);
    }
}
