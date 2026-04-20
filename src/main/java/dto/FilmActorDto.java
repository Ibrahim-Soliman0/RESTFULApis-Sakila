package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class FilmActorDto {
    private Integer actorId;
    private Integer filmId;
    private Instant lastUpdate;
}