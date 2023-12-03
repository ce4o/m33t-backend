package pl.kj.m33t.m33tbackend.model.entity.modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="post")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post extends EventModule{
    private String title;

    private String description;
}