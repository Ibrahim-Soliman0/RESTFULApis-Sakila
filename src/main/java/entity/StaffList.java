package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Entity
@Immutable
@Table(name = "staff_list", schema = "sakila")
public class StaffList {

    @Id
    @Column(name = "ID", columnDefinition = "tinyint UNSIGNED not null")
    private Short id;

    @Size(max = 91)
    @Column(name = "name", length = 91)
    private String name;

    @Size(max = 50)
    @NotNull
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Size(max = 10)
    @Column(name = "`zip code`", length = 10)
    private String zipCode;

    @Size(max = 20)
    @NotNull
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Size(max = 50)
    @NotNull
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Size(max = 50)
    @NotNull
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "SID", columnDefinition = "tinyint UNSIGNED not null")
    private Short sid;


}