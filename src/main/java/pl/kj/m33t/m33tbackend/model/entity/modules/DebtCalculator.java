package pl.kj.m33t.m33tbackend.model.entity.modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Table(name="debcalulator")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DebtCalculator extends EventModule{
    private String title;

    @OneToMany(mappedBy = "debtCalculator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> purchases  = new ArrayList<>();
}