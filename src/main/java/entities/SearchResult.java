package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
    private int id;
    private String search;
    private Thumbnail thumbnail;

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }
}
