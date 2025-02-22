package com.whh.findmuseapi.post.entity;

import com.whh.findmuseapi.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;
    private boolean activeStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Bookmark(Post post, User user) {
        this.activeStatus = false;
        setRelation(post, user);
    }

    private void setRelation(Post post, User user) {
        this.post = post;
        this.user = user;
        user.getBookmarks().add(this);
    }

    public void changeStatus() {
        this.activeStatus = !this.activeStatus;
    }
}
