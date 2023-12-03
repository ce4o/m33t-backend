package pl.kj.m33t.m33tbackend.model.repository.modules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kj.m33t.m33tbackend.model.entity.modules.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {}