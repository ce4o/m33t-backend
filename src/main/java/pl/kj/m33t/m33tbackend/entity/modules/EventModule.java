package pl.kj.m33t.m33tbackend.entity.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kj.m33t.m33tbackend.entity.Event;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EventModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "event")
    @JsonIgnore
    protected Event event;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date date;

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }
}
