package com.whh.findmuseapi.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ComplaintUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_user")
    private Long id;
    private boolean activeStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
