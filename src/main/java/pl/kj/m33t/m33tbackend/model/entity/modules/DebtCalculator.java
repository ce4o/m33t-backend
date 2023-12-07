package pl.kj.m33t.m33tbackend.model.entity.modules;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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