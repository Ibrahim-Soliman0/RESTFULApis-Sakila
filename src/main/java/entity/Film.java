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

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "film", schema = "sakila")
@XmlRootElement
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
    @XmlElement
    private Integer id;

    @Size(max = 128)
    @NotNull
    @Column(name = "title", nullable = false, length = 128)
    @XmlElement
    private String title;

    @Lob
    @Column(name = "description")
    @XmlElement
    private String description;

    @Column(name = "release_year")
    @XmlElement
    private Integer releaseYear;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @ColumnDefault("'3'")
    @Column(name = "rental_duration", columnDefinition = "tinyint UNSIGNED not null")
    @XmlElement
    private Short rentalDuration;

    @NotNull
    @ColumnDefault("4.99")
    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    @XmlElement
    private BigDecimal rentalRate;

    @Column(name = "length", columnDefinition = "smallint UNSIGNED")
    @XmlElement
    private Integer length;

    @NotNull
    @ColumnDefault("19.99")
    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    @XmlElement
    private BigDecimal replacementCost;

    @ColumnDefault("'G'")
    @Lob
    @Column(name = "rating")
    @XmlElement
    private String rating;

    @Lob
    @Column(name = "special_features")
    @XmlElement
    private String specialFeatures;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_update", nullable = false)
    @XmlElement
    private Instant lastUpdate;

    @JsonbTransient
    @OneToMany(mappedBy = "film")
    private Set<FilmActor> filmActors = new LinkedHashSet<>();

    @JsonbTransient
    @OneToMany(mappedBy = "film")
    private Set<FilmCategory> filmCategories = new LinkedHashSet<>();

    @JsonbTransient
    @OneToMany(mappedBy = "film")
    private Set<Inventory> inventories = new LinkedHashSet<>();

}