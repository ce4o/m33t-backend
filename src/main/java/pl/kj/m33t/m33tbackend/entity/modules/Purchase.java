package pl.kj.m33t.m33tbackend.entity.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="purchase")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="purchase_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "debtcalculator_id")
    @JsonIgnore
    private DebtCalculator debtCalculator;

    private Long creditorId;

    private Long debtorId;

    @ElementCollection
    @CollectionTable(name = "item_price_mapping",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "purchase_id")})
    @MapKeyColumn(name = "item_name")
    @Column(name = "price")
    private Map<String, BigDecimal> itemPriceMap = new HashMap<>();
}