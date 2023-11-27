package pl.kj.m33t.m33tbackend.entity.modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name="survey")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Survey extends EventModule{
    private String sentence;

    @ElementCollection
    @MapKeyColumn(name = "options")
    @Column(name = "votes")
    @CollectionTable(name = "survey_options", joinColumns = @JoinColumn(name = "survey_id"))
    private Map<String, Integer> optionsAndVotes = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "survey_voters", joinColumns = @JoinColumn(name = "survey_id"))
    @Column(name = "user_id")
    private Set<Long> voters = new HashSet<>();
}