package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorInfoDto {
    private Integer actorId;
    private String firstName;
    private String lastName;
    private String filmInfo;
}