package id.hanifu.cendolin_donation_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import id.hanifu.cendolin_donation_service.enums.PaymentStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;

@Getter
@Setter
@Entity
@Table(name = "transactions", uniqueConstraints = {
        @UniqueConstraint(columnNames = "trx_id")
})
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private BigInteger id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private DonationEntity donationEntity;

    @Column(name = "trx_id")
    @JsonProperty("trx_id")
    private String transactionId;

    @Column(name = "customer_name")
    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("customer_email")
    @Column(name = "customer_email")
    private String customerEmail;

    @JsonProperty("customer_phone")
    @Column(name = "customer_phone")
    @Nullable
    private String customerPhone;

    @JsonProperty("payment_status")
    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @JsonProperty("payment_gateway_url")
    @Column(name = "payment_gateway_url")
    @Nullable
    private String paymentGatewayUrl;

    @JsonProperty("qris_string")
    @Column(name = "qris_string")
    @Nullable
    private String qrisString;

    @JsonProperty("amount")
    @Column(name = "amount")
    private BigDecimal amount;

    @JsonProperty("created_at")
    @Column(name = "created_at")
    @CreationTimestamp
    private Time createdAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Time updatedAt;
}
