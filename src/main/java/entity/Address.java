package entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "address", schema = "sakila")
@XmlRootElement
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", columnDefinition = "smallint UNSIGNED not null")
    @XmlElement
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "address", nullable = false, length = 50)
    @XmlElement
    private String address;

    @Size(max = 50)
    @Column(name = "address2", length = 50)
    @XmlElement
    private String address2;

    @Size(max = 20)
    @NotNull
    @Column(name = "district", nullable = false, length = 20)
    @XmlElement
    private String district;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Size(max = 10)
    @Column(name = "postal_code", length = 10)
    @XmlElement
    private String postalCode;

    @Size(max = 20)
    @NotNull
    @Column(name = "phone", nullable = false, length = 20)
    @XmlElement
    private String phone;

    @Column(name = "location", columnDefinition = "geometry not null")
    @XmlElement
    private Object location;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_update", nullable = false)
    @XmlElement
    private Instant lastUpdate;

    @JsonbTransient
    @OneToMany(mappedBy = "address")
    private Set<Customer> customers = new LinkedHashSet<>();

    @JsonbTransient
    @OneToMany(mappedBy = "address")
    private Set<Staff> staff = new LinkedHashSet<>();

    @JsonbTransient
    @OneToMany(mappedBy = "address")
    private Set<Store> stores = new LinkedHashSet<>();

}