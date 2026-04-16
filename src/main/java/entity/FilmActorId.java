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
public class FilmActorId implements Serializable {
    private static final long serialVersionUID = 8394830534687457501L;
    @Column(name = "actor_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer actorId;

    @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer filmId;


}