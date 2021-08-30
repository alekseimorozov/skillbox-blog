package ru.skillbox.skillboxblog.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EntityTest {
    @Autowired
    private TestEntityManager em;
    private User userExpected;
    private Post postExpected;
    private Comment parentComment;
    private Comment childComment;
    private Tag tag;

    @BeforeEach
    public void initExpected() {
        userExpected = User.builder()
                .name("Test")
                .email("test@test.com")
                .password("qwerty")
                .code("test_code")
                .posts(new ArrayList<>())
                .comments(new ArrayList<>())
                .regTime(LocalDateTime.now())
                .build();
        postExpected = Post.builder()
                .moderationStatus(ModerationStatus.NEW)
                .title("Test post")
                .moderator(userExpected)
                .user(userExpected)
                .text("Test test test")
                .viewCount(new AtomicInteger(0))
                .comments(new ArrayList<>())
                .build();
        parentComment = Comment.builder()
                .post(postExpected)
                .user(userExpected)
                .text("test parent comment")
                .build();
        childComment = Comment.builder()
                .parent(parentComment)
                .post(parentComment.getPost())
                .user(userExpected)
                .text("test child comment")
                .build();
        tag = Tag.builder().name("@Test").build();
    }

    @Test
    @DisplayName("User entity was persisted and found correctly")
    public void userEntityTest() {
        em.persist(userExpected);
        em.flush();
        em.clear();
        User userActual = em.find(User.class, userExpected.getId());
        assertEquals(userExpected, userActual, () -> "User find by id is not correct");
    }

    @Test
    @DisplayName("Entity Post was persisted and found correctly")
    public void postEntityTest() {
        userExpected.addPost(postExpected);
        em.persist(userExpected);
        em.persist(postExpected);
        em.flush();
        em.clear();
        Post postActual = em.find(Post.class, postExpected.getId());
        assertEquals(postExpected, postActual, () -> "Entity Post find by id is not correct");
    }

    @Test
    @DisplayName("Comment entity is correctly persisted and then found")
    public void commentEntityTest() {
        userExpected.addComment(parentComment);
        userExpected.addComment(childComment);
        userExpected.addPost(postExpected);
        postExpected.setUser(userExpected);
        postExpected.addComment(parentComment);
        em.persist(userExpected);
        em.persist(postExpected);
        em.persist(parentComment);
        em.persist(childComment);
        em.flush();
        em.clear();
        Comment commentActual = em.find(Comment.class, childComment.getId());
        assertEquals(childComment, commentActual);
    }

    @Test
    @DisplayName("PostVote entity is correctly persisted and found")
    public void postVotesTest() {
        PostVote postVote = PostVote.builder()
                .user(userExpected)
                .post(postExpected)
                .value(1)
                .build();
        em.persist(userExpected);
        em.persist(postExpected);
        em.persist(postVote);
        em.flush();
        em.clear();
        PostVote postVoteActual = em.find(PostVote.class, postVote.getId());
        assertEquals(postVote, postVoteActual);
    }

    @Test
    @DisplayName("Tag entity is correctly persisted and found")
    public void tagEntityTest() {
        em.persist(tag);
        em.flush();
        em.clear();
        assertEquals(tag, em.find(Tag.class, tag.getId()));
    }

    @Test
    @DisplayName("TagToPost entity is correctly persisted and found")
    public void tagToPostEntityTest() {
        postExpected.setModerator(userExpected);
        postExpected.setUser(userExpected);
        TagToPost ttp = TagToPost.builder()
                .post(postExpected)
                .tag(tag)
                .build();
        em.persist(userExpected);
        em.persist(postExpected);
        em.persist(tag);
        em.persist(ttp);
        em.flush();
        em.clear();
        assertEquals(ttp, em.find(TagToPost.class, ttp.getId()));
    }

    @Test
    @DisplayName("CapchaCode entity is correctly persisted and found")
    public void capchaCodeEntityTest() {
        CapchaCode cc = CapchaCode.builder()
                .code("test")
                .secret_code("test")
                .build();
        em.persist(cc);
        em.flush();
        em.clear();
        assertEquals(cc, em.find(CapchaCode.class, cc.getId()));
    }

    @Test
    @DisplayName("GlobalSetting entity is correctly persisted and found")
    public void globalSettingEntityTest() {
        GlobalSetting gs = GlobalSetting.builder()
                .code("test")
                .name("test")
                .value("test")
                .build();
        em.persist(gs);
        em.flush();
        em.clear();
        assertEquals(gs, em.find(GlobalSetting.class, gs.getId()));
    }
}