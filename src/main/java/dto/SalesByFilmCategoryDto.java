package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SalesByFilmCategoryDto {
    private String category;
    private BigDecimal totalSales;
}