package id.hanifu.cendolin_donation_service.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserCreatedObject {
    @JsonProperty("id")
    private String id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    @JsonProperty("suspended_reason")
    private String suspendedReason;

    @JsonProperty("verified")
    private boolean isVerified;

    @JsonProperty("country")
    private String country;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("password_hash")
    private String passwordHash;

    public UserCreatedObject(String id, String email, String username, String suspendedReason, boolean isVerified, String country, String createdAt, String updatedAt, String passwordHash) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.suspendedReason = suspendedReason;
        this.isVerified = isVerified;
        this.country = country;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.passwordHash = passwordHash;
    }
}
