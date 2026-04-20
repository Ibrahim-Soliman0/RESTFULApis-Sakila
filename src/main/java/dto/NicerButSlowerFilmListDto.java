package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class NicerButSlowerFilmListDto {
    private Integer fid;
    private String title;
    private String description;
    private String category;
    private BigDecimal price;
    private Integer length;
    private String rating;
    private String actors;
}