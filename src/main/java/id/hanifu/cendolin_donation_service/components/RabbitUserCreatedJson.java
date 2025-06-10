package id.hanifu.cendolin_donation_service.components;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import id.hanifu.cendolin_donation_service.objects.UserCreatedObject;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;

@JsonComponent
public class RabbitUserCreatedJson {
    public static class Deserializer extends JsonObjectDeserializer<UserCreatedObject> {
        @Override
        protected UserCreatedObject deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
            String id = tree.get("id").asText();
            String email = tree.get("email").asText();
            String username = tree.get("username").asText();
            String suspendedReason = nullSafeValue(tree.get("suspended_reason"), String.class);
            String country = tree.get("country").asText();
            boolean isVerified = tree.get("verified").asBoolean();
            String createdAt = tree.get("created_at").asText();
            String updatedAt = tree.get("updated_at").asText();
            String hash = tree.get("password_hash").asText();

            return new UserCreatedObject(
                    id,
                    email,
                    username,
                    suspendedReason,
                    isVerified,
                    country,
                    createdAt,
                    updatedAt,
                    hash
            );
        }
    }
}
