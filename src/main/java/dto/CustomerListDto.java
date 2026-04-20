package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerListDto {
    private Integer id;
    private String name;
    private String address;
    private String zipCode;
    private String phone;
    private String city;
    private String country;
    private String notes;
    private Short sid;
}