package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Getter
@Entity
@Immutable
@Table(name = "sales_by_store", schema = "sakila")
public class SalesByStore {

    @Id
    @Size(max = 101)
    @Column(name = "store", length = 101)
    private String store;

    @Size(max = 91)
    @Column(name = "manager", length = 91)
    private String manager;

    @Column(name = "total_sales", precision = 27, scale = 2)
    private BigDecimal totalSales;


}