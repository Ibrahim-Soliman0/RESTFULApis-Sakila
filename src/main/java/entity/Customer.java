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
@Table(name = "customer", schema = "sakila")
@XmlRootElement
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", columnDefinition = "smallint UNSIGNED not null")
    @XmlElement
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Size(max = 45)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 45)
    @XmlElement
    private String firstName;

    @Size(max = 45)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 45)
    @XmlElement
    private String lastName;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    @XmlElement
    private String email;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "active", nullable = false)
    @XmlElement
    private Boolean active;

    @NotNull
    @Column(name = "create_date", nullable = false)
    @XmlElement
    private Instant createDate;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_update")
    @XmlElement
    private Instant lastUpdate;

    @JsonbTransient
    @OneToMany(mappedBy = "customer")
    private Set<Payment> payments = new LinkedHashSet<>();

    @JsonbTransient
    @OneToMany(mappedBy = "customer")
    private Set<Rental> rentals = new LinkedHashSet<>();

}