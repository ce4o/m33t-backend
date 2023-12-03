package pl.kj.m33t.m33tbackend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kj.m33t.m33tbackend.model.entity.modules.EventModule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    private String name;

    private Date date;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "event_participants",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )
    private List<User> participants = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventModule> eventModulesList = new ArrayList<>();

    private String description;

    public void addParticipant(User user){
        participants.add(user);
    }
}