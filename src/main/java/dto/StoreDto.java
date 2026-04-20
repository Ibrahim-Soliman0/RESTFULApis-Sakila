package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class StoreDto {
    private Short id;
    private Integer addressId;
    private Short managerStaffId;
    private Instant lastUpdate;
}