package id.hanifu.cendolin_donation_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "donations", uniqueConstraints = {
        @UniqueConstraint(columnNames = "invoice_id")
})
public class DonationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private UserEntity userEntity;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", referencedColumnName = "trx_id")
    @JsonProperty("transaction")
    private TransactionEntity transactionEntity;

//    Invoice ID generated using UUID
    @Column(name = "invoice_id")
    @JsonProperty("invoice_id")
    private String invoiceId;

//    Amount / Donation amount
    @Column(name = "amount")
    @JsonProperty("amount")
    private BigDecimal price;

//    Jenis Pembayaran
    @JsonProperty("payment_type")
    @Column(name = "payment_type")
    private String paymentType;

//    Jenis currency/mata uang
    @Column(name = "currency")
    @JsonProperty("currency")
    private String currency;

//    Email donatur
    @JsonProperty("donator_email")
    @Column(name = "donator_email")
    private String donatorEmail;

//    Nama donatur
    @Column(name = "donator_name")
    @JsonProperty("donator_name")
    private String donatorName;

//    Phone donatur
    @JsonProperty("donator_phone")
    @Column(name = "donator_phone")
    @Nullable
    private String donatorPhone;

//    Donate message
    @JsonProperty("message")
    @Column(name = "message")
    @Nullable
    private String message;

//    ---- TIMESTAMPS ----
    @JsonProperty("created_at")
    @Column(name = "created_at")
    @CreationTimestamp
    private Time createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonProperty("updated_at")
    private Time updatedAt;
}
