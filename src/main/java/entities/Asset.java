package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
public class Asset {
    @JsonProperty("content_type")
    private String contentType;
    @JsonProperty("external_id")
    private String externalID;
    private AssetValues values;
    @JsonIgnore
    private int pk;
}
