package ru.skillbox.skillboxblog.model;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_votes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class PostVote {
    @Id
    @GeneratedValue
    @ToString.Include(rank = 10)
    @EqualsAndHashCode.Include(rank = 10)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
    @UpdateTimestamp
    private LocalDateTime time;
    @ToString.Include(rank = 8)
    @EqualsAndHashCode.Include(rank = 8)
    private int value;
}