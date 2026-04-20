package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CustomerDto {
    private Integer id;
    private Short storeId;
    private Integer addressId;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;
    private Instant createDate;
    private Instant lastUpdate;
}