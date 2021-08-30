package ru.skillbox.skillboxblog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data()
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue
    @ToString.Include(rank = 7)
    @EqualsAndHashCode.Include(rank = 7)
    private Integer id;
    @Column(name = "is_moderator", nullable = false)
    private boolean isModerator;
    @CreationTimestamp
    @Column(name = "reg_time", nullable = false)
    private LocalDateTime regTime;
    @Column(nullable = false)
    @ToString.Include(rank = 5)
    @EqualsAndHashCode.Include(rank = 5)
    private String name;
    @Column(nullable = false)
    @ToString.Include(rank = 3)
    @EqualsAndHashCode.Include(rank = 3)
    private String email;
    @Column(nullable = false)
    private String password;
    private String code;
    @Column(length = 1024)
    private String text;
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public boolean addPost(Post post) {
        return this.posts.add(post);
    }

    public boolean removePost(Post post) {
        return this.posts.remove(post);
    }

    public boolean addComment(Comment comment) {
        return this.comments.add(comment);
    }

    public boolean removeComment(Comment comment) {
        return this.comments.remove(comment);
    }
}