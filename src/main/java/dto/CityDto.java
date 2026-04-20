package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CityDto {
    private Integer id;
    private Integer countryId;
    private String city;
    private Instant lastUpdate;
}