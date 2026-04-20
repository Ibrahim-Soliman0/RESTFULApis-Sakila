package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CountryDto {
    private Integer id;
    private String country;
    private Instant lastUpdate;
}