package dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class StaffDto {
    private Short id;
    private String firstName;
    private String lastName;
    private Integer addressId;
    private byte[] picture;
    private String email;
    private Short storeId;
    private Boolean active;
    private String username;
    private String password;
    private Instant lastUpdate;
}