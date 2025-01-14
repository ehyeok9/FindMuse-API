package com.whh.findmuseapi.post.repository;

import com.whh.findmuseapi.common.constant.Infos;
import com.whh.findmuseapi.post.entity.Post;
import com.whh.findmuseapi.post.entity.Volunteer;
import com.whh.findmuseapi.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    Long countByPostAndStatus(Post post, Infos.InvieteStatus status);

    boolean existsByUserAndPost(User user, Post post);

    List<Volunteer> findByPostIdAndActiveStatusTrueAndStatus(Long postId, Infos.InvieteStatus status);
    List<Volunteer> findByPostIdAndActiveStatusTrueAndStatusNot(Long postId, Infos.InvieteStatus status);
    List<Volunteer> findByUserIdAndStatusAndActiveStatusTrue(Long userId, Infos.InvieteStatus status);

}