package id.hanifu.cendolin_donation_service.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class ResponseObject<T> {
    @JsonProperty("data")
    @Nullable
    private T data;

    @JsonProperty("errors")
    @Nullable
    private Map<String, String> errors;
}
