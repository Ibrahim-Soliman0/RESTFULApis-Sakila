package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SalesByStoreDto {
    private String store;
    private String manager;
    private BigDecimal totalSales;
}