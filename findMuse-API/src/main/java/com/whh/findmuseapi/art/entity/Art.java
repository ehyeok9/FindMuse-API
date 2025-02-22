package com.whh.findmuseapi.art.entity;

import com.whh.findmuseapi.file.entity.File;
import com.whh.findmuseapi.review.entity.ArtReview;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.whh.findmuseapi.common.constant.Infos.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_id")
    private Long id;
    private int randomId;
    private String title;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String location;
    private String place;
    private String startDate;
    private String endDate;
    @Column(length = 5000)
    private String startTime;
    private String age;
    private String latitude;    //위도
    private String longitude;   //경도
    private String sPark;       //장애인 주차장 여부
    private String park;        //주차장 여부
    private float star;           //평점

//    @OneToOne
//    @JoinColumn(name = "setlist_id")
//    private SetList setList;

    @OneToMany(mappedBy = "art", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ArtReview> artReviews = new ArrayList<>();

    @OneToMany(mappedBy = "art", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<File> files = new ArrayList<>();

    @OneToMany(mappedBy = "art", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "art", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArtLike> artLikes = new ArrayList<>();

    @OneToMany(mappedBy = "art", fetch = FetchType.LAZY)
    private List<ArtHistory> artHistories = new ArrayList<>();

    @Builder
    public Art(String title, int randomId, Genre genre, String place, String startDate, String endDate, String startTime, String age) {
        this.title = title;
        this.randomId = randomId;
        this.genre = genre;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.age = age;
    }

    public void setFilesAndTickets(List<File> files, List<Ticket> tickets) {
        this.files = files;
        this.tickets = tickets;
    }

    public void setPlaceDetailInfo(String location, String latitude, String longitude, String sPark, String park) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sPark = sPark;
        this.park = park;
    }

    // 메인포스터 반환 함수
    public String getPoster() {
        String poster = "Empty";
        if (!this.files.isEmpty()) {
            poster = files.get(0).getUrl();
        }
        return poster;
    }

    public void plusViewAndCalStar(float star) {
        int viewCnt = this.artHistories.size();
        this.star = ((this.star * viewCnt) + star) / (viewCnt+1);
    }

    public void updateStar(float oldStar, float newStar) {
        int viewCnt = this.artHistories.size();
        this.star = ((this.star * viewCnt) - oldStar + newStar) / viewCnt;
    }
}
