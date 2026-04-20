package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class LanguageDto {
    private Short id;
    private String name;
    private Instant lastUpdate;
}