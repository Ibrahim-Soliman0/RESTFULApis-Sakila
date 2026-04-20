package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class InventoryDto {
    private Integer id;
    private Integer filmId;
    private Short storeId;
    private Instant lastUpdate;
}