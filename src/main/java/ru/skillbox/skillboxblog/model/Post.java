package ru.skillbox.skillboxblog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {
    @Id
    @GeneratedValue
    @ToString.Include(rank = 9)
    @EqualsAndHashCode.Include(rank = 9)
    private Integer id;
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "moderation_status", nullable = false)
    @ToString.Include(rank = 7)
    @EqualsAndHashCode.Include(rank = 7)
    private ModerationStatus moderationStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User moderator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime time;
    @Column(nullable = false)
    @ToString.Include(rank = 5)
    @EqualsAndHashCode.Include(rank = 5)
    private String title;
    @Column(length = 1024, nullable = false)
    private String text;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
    @Column(nullable = false)
    private AtomicInteger viewCount;

    public void count() {
        viewCount.incrementAndGet();
    }

    public boolean addComment(Comment comment) {
        return this.comments.add(comment);
    }

    public boolean removeComment(Comment comment) {
        return this.comments.remove(comment);
    }
}