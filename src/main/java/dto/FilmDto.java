package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class FilmDto {
    private Integer id;
    private String title;
    private String description;
    private Integer releaseYear;
    private Short languageId;
    private Short originalLanguageId;
    private Short rentalDuration;
    private BigDecimal rentalRate;
    private Integer length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;
    private Instant lastUpdate;
}