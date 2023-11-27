package pl.kj.m33t.m33tbackend.entity.modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="lottery")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Lottery extends EventModule{
    private String sentence;

    @ElementCollection
    @CollectionTable(name = "option_list", joinColumns = @JoinColumn(name = "lottery_id"))
    @Column(name = "option")
    private List<String> options  = new ArrayList<>();

    private String result;
}