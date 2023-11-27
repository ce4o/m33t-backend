package pl.kj.m33t.m33tbackend.dao.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kj.m33t.m33tbackend.entity.modules.Lottery;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, Long> {}