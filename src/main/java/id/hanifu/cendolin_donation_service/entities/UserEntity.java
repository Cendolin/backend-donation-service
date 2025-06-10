package id.hanifu.cendolin_donation_service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.sql.Time;
import java.util.List;

@Getter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id"),
        @UniqueConstraint(columnNames = "email")
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DonationEntity> donationEntities;

    @Column(name = "user_id")
    @Setter
    private String userId;

    @Column(name = "email")
    @Setter
    private String email;

    @Column(name = "username")
    @Setter
    private String username;

    @Column(name = "created_at")
    @Setter
    @CreationTimestamp
    private Time createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Setter
    private Time updatedAt;
}
