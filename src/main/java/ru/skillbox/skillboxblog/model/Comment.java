package ru.skillbox.skillboxblog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_comments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comment {
    @Id
    @GeneratedValue
    @ToString.Include(rank = 10)
    @EqualsAndHashCode.Include(rank = 10)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime time;
    @Column(length = 2048, nullable = false)
    @ToString.Include(rank = 8)
    @EqualsAndHashCode.Include(rank = 8)
    private String text;
}