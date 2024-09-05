package com.whh.findmuseapi.post.service;

import com.whh.findmuseapi.post.dto.request.PostCreateRequest;
import com.whh.findmuseapi.post.dto.request.PostUpdateRequest;
import com.whh.findmuseapi.post.dto.response.PostListResponse;
import com.whh.findmuseapi.post.dto.response.PostOneReadResponse;
import com.whh.findmuseapi.post.entity.Post;
import com.whh.findmuseapi.user.entity.User;

/**
 * class: PostService.
 * 게시글 관련 서비스 입니다.
 *
 * @author devminseo
 * @version 8/20/24
 */

public interface PostService {


    /**
     * 게시물 생성 로직입니다.
     *
     * @param createRequest 게시물 생성 정보
     */
    void createPost(PostCreateRequest createRequest);

    /**
     * 게시물 단일 조회 로직입니다.
     *
     * @param postId 게시글 아이디
     * @return 게시글
     */
    PostOneReadResponse readPost(Long postId, Long userId);

    /**
     * 게시물 수정 로직입니다.
     *
     * @param updateRequest 수정 사항
     */
    void updatePost(PostUpdateRequest updateRequest);

    /**
     * 회원 유효성 검사
     * 게시물 유효성 검사
     * 회원 글쓴이 검사
     *
     * @param userId 글쓴이 아이디
     * @param postId 게시글 아이디
     */
    void deletePost(Long userId, Long postId);

    /**
     * 회원이 모집글을 쓴 사람인지 체크합니다.

     */
    void checkWriter(User user, Post post);

    /**
     * 모집글 리스트를 가져옵니다.
     */
    PostListResponse getPostList();

}
