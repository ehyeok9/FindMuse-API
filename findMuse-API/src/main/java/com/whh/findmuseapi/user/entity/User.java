package com.whh.findmuseapi.user.entity;

import com.whh.findmuseapi.alarm.entity.Alarm;
import com.whh.findmuseapi.art.entity.ArtHistory;
import com.whh.findmuseapi.art.entity.ArtLike;
import com.whh.findmuseapi.chat.entity.Chat;
import com.whh.findmuseapi.common.constant.Infos.Role;
import com.whh.findmuseapi.file.entity.File;
import com.whh.findmuseapi.post.entity.Bookmark;
import com.whh.findmuseapi.post.entity.Post;
import com.whh.findmuseapi.post.entity.Volunteer;
import com.whh.findmuseapi.review.entity.ArtReview;
import com.whh.findmuseapi.review.entity.ArtReviewLike;
import com.whh.findmuseapi.review.entity.UserReview;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.whh.findmuseapi.common.constant.Infos.LoginType;
import static com.whh.findmuseapi.common.constant.Infos.Gender;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String accountId;
    private String email;
    private String nickname;
    private int birthYear;
    @Column(length = 512)
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String location;
    private String comment;
    private int artCount = 0; //참여한 전시 횟수
    private int findMuseCount = 0; // 뮤즈 찾기 횟수
    private boolean showStatus = false;
    private boolean isOnboardingFinished = false; // 최초 로그인 시 온보딩 진행 여부
    private boolean alarmStatus;
    private boolean activateStatus;
    private LoginType loginType;
    
    private String refreshToken;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File photo;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTaste> userTastes = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArtLike> artLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ArtHistory> histories = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ArtReview> artReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ArtReviewLike> reviewLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Chat> chatList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Volunteer> volunteeredList = new ArrayList<>();

    //내가 받은 리뷰 목록
    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<UserReview> userReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Alarm> alarmList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BlackUser> blackList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ComplaintUser> complaintList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserTaste> tasteList = new ArrayList<>();
    
    @Builder
    public User(String accountId, String email, Role role, String refreshToken, String nickname) {
        this.accountId = accountId;
        this.email = email;
        this.role = role;
        this.refreshToken = refreshToken;
        this.nickname = nickname;
    }

    public void updateInformation(Integer birthYear, Gender gender) {
        this.birthYear = birthYear;
        this.gender = gender;
    }

    public void authorizeUser() {
        this.role = Role.USER;
    }
    
    public void updateRefreshToken(String updatedRefreshToken) {
        this.refreshToken = updatedRefreshToken;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public void updateIsOnboardingFinished() {
        this.isOnboardingFinished = true;
    }

    public void updateLocation(String location) {
        this.location = location;
    }

    public void updateProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void updateProfile(String comment, boolean showStatus) {
        this.comment = comment;
        this.showStatus = showStatus;
    }
}
