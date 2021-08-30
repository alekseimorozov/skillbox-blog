package ru.skillbox.skillboxblog.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "tag2post")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TagToPost {
    @Id
    @GeneratedValue
    @ToString.Include
    @EqualsAndHashCode.Include
    private Integer id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Tag tag;
}