package guru.qa.restbackend.lombok.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookRequestSuccessfulAdd {

    private String book = "Sunny";
}
