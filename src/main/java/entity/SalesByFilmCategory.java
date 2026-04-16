package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Getter
@Entity
@Immutable
@Table(name = "sales_by_film_category", schema = "sakila")
public class SalesByFilmCategory {

    @Id
    @Size(max = 25)
    @NotNull
    @Column(name = "category", nullable = false, length = 25)
    private String category;

    @Column(name = "total_sales", precision = 27, scale = 2)
    private BigDecimal totalSales;


}