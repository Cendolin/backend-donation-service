package id.hanifu.cendolin_donation_service.entities;

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
@Table(name = "donations", uniqueConstraints = {
        @UniqueConstraint(columnNames = "invoice_id")
})
public class DonationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "trx_id")
    private TransactionEntity transactionEntity;

//    Invoice ID generated using UUID
    @Column(name = "invoice_id")
    private String invoiceId;

//    Amount / Donation amount
    @Column(name = "amount")
    private BigDecimal price;

//    Jenis Pembayaran
    @Column(name = "payment_type")
    private String paymentType;

//    Jenis currency/mata uang
    @Column(name = "currency")
    private String currency;

//    Email donatur
    @Column(name = "donator_email")
    private String donatorEmail;

//    Nama donatur
    @Column(name = "donator_name")
    private String donatorName;

//    Phone donatur
    @Column(name = "donator_phone")
    @Nullable
    private String donatorPhone;

//    Donate message
    @Column(name = "message")
    @Nullable
    private String message;

//    ---- TIMESTAMPS ----
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
