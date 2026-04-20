package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AddressDto {
    private Integer id;
    private String address;
    private String address2;
    private String district;
    private Integer cityId;
    private String postalCode;
    private String phone;
    private Object location;
    private Instant lastUpdate;
}