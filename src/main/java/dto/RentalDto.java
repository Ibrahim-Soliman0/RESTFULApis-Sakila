package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RentalDto {
    private Integer id;
    private Instant rentalDate;
    private Integer inventoryId;
    private Integer customerId;
    private Instant returnDate;
    private Integer staffId;
    private Instant lastUpdate;
}