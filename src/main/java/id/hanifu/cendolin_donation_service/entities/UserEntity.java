package id.hanifu.cendolin_donation_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("id")
    private BigInteger id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DonationEntity> donationEntities;

    @JsonProperty("user_id")
    @Column(name = "user_id")
    @Setter
    private String userId;

    @Column(name = "email")
    @Setter
    private String email;

    @JsonProperty("username")
    @Column(name = "username")
    @Setter
    private String username;

    @JsonProperty("created_at")
    @Column(name = "created_at")
    @Setter
    @CreationTimestamp
    private Time createdAt;

    @JsonProperty("updated_at")
    @UpdateTimestamp
    @Column(name = "updated_at")
    @Setter
    private Time updatedAt;
}
