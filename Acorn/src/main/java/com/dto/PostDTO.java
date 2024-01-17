package com.dto;

import java.sql.Timestamp;
import java.util.Date;

public class PostDTO {
    private Long postId;
    private String postBoard;
    private String userId;
    private Long contId;
    private String postTitle;
    private Date postDate;
    private Date postEditDate;
    private String postText;

    // 생성자
    public PostDTO() {
    }
    
    // 사용자 지정 생성자
    public PostDTO(String userId, String postTitle, String postText, String postBoard) {
        this.userId = userId;
        this.postTitle = postTitle;
        this.postText = postText;
        this.postBoard = postBoard;
    }

    @Override
	public String toString() {
		return "PostDTO [postId=" + postId + ", postBoard=" + postBoard + ", userId=" + userId + ", contId=" + contId
				+ ", postTitle=" + postTitle + ", postDate=" + postDate + ", postEditDate=" + postEditDate
				+ ", postText=" + postText + "]";
	}

	// 게터 및 세터
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getPostEditDate() {
        return postEditDate;
    }

    public void setPostEditDate(Date postEditDate) {
        this.postEditDate = postEditDate;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
}

