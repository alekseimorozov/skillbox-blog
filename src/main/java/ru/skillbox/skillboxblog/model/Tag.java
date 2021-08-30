package ru.skillbox.skillboxblog.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "tags")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Tag {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String name;
}