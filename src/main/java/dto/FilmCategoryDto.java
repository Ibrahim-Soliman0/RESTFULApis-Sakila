package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class FilmCategoryDto {
    private Integer filmId;
    private Short categoryId;
    private Instant lastUpdate;
}