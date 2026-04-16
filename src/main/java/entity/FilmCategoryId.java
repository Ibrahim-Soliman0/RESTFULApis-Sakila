package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class FilmCategoryId implements Serializable {
    private static final long serialVersionUID = -4857289182230825789L;
    @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer filmId;

    @Column(name = "category_id", columnDefinition = "tinyint UNSIGNED not null")
    private Short categoryId;


}