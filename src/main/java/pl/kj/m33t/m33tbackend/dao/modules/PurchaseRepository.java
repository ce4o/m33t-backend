package pl.kj.m33t.m33tbackend.dao.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kj.m33t.m33tbackend.entity.User;
import pl.kj.m33t.m33tbackend.entity.modules.Purchase;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByCreditorIdContains(User user);

    List<Purchase> findByDebtorIdContains(User user);
}