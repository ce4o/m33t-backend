package pl.kj.m33t.m33tbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kj.m33t.m33tbackend.model.entity.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {}