package pl.kj.m33t.m33tbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kj.m33t.m33tbackend.entity.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {}