package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ActorDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Instant lastUpdate;
}