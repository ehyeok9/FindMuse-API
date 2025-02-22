package com.whh.findmuseapi.art.entity;

import com.whh.findmuseapi.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_like_id")
    private Long id;
    private boolean activeStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "art_id")
    private Art art;

    public ArtLike(User user, Art art) {
        this.activeStatus = false;
        this.user = user;
        this.art = art;
        setEntity(user, art);
    }

    private void setEntity(User user, Art art) {
        user.getArtLikes().add(this);
        art.getArtLikes().add(this);
    }

    public void changeStatus() {
        this.activeStatus = !this.activeStatus;
    }
}
