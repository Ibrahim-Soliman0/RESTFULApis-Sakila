package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Getter
@Entity
@Immutable
@Table(name = "nicer_but_slower_film_list", schema = "sakila")
public class NicerButSlowerFilmList {
    @Id
    @Column(name = "FID", columnDefinition = "smallint UNSIGNED not null")
    private Integer fid;

    @Size(max = 128)
    @NotNull
    @Column(name = "title", nullable = false, length = 128)
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Size(max = 25)
    @Column(name = "category", length = 25)
    private String category;

    @NotNull
    @Column(name = "price", nullable = false, precision = 4, scale = 2)
    private BigDecimal price;

    @Column(name = "length", columnDefinition = "smallint UNSIGNED")
    private Integer length;

    @Lob
    @Column(name = "rating")
    private String rating;

    @Lob
    @Column(name = "actors")
    private String actors;


}