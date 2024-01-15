package com.domain.dto;
import java.sql.Timestamp;

public class PostDTO {

    private Long postId;
    private String postBoard;
    private Long userId;
    private Long contId = 4L;
    private String postTitle;
    private Timestamp postDate;
    private String postText;
    private Long viewNum;
    private Long likeNum;

    // 기본 생성자
    public PostDTO() {
    }

    // 글 작성용 생성자
    public PostDTO(Long userId, String postTitle, String postText, String postBoard) {
        this.userId = userId;
        this.postTitle = postTitle;
        this.postText = postText;
        this.postBoard = postBoard;
        this.viewNum = 0L;
        this.likeNum = 0L;
    }
    
    // 매개변수를 받는 생성자
    public PostDTO(Long postId, String postBoard, Long userId, Long contId, String postTitle,
                   Timestamp postDate, String postText, Long viewNum, Long likeNum) {
        this.postId = postId;
        this.postBoard = postBoard;
        this.userId = userId;
        this.contId = contId;
        this.postTitle = postTitle;
        this.postDate = postDate;
        this.postText = postText;
        this.viewNum = viewNum;
        this.likeNum = likeNum;
    }

    // Getter 및 Setter 메서드들
    public Long getPostId() {
        return postId;
    }
    

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostBoard() {
        return postBoard;
    }

    public void setPostBoard(String postBoard) {
        this.postBoard = postBoard;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContId() {
        return contId;
    }

    public void setContId(Long contId) {
        this.contId = contId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId=" + postId +
                ", postBoard='" + postBoard + '\'' +
                ", userId='" + userId + '\'' +
                ", contId=" + contId +
                ", postTitle='" + postTitle + '\'' +
                ", postDate=" + postDate +
                ", postText='" + postText + '\'' +
                ", viewNum=" + viewNum +
                ", likeNum=" + likeNum +
                '}';
    }
}
