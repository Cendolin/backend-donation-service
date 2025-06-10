package id.hanifu.cendolin_donation_service.entities;

import id.hanifu.cendolin_donation_service.enums.PaymentStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transactions", uniqueConstraints = {
        @UniqueConstraint(columnNames = "trx_id")
})
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @OneToOne(cascade = CascadeType.ALL)
    private DonationEntity donationEntity;

    @Column(name = "trx_id")
    private String transactionId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_phone")
    @Nullable
    private String customerPhone;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "payment_gateway_url")
    @Nullable
    private String paymentGatewayUrl;

    @Column(name = "qris_string")
    @Nullable
    private String qrisString;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
