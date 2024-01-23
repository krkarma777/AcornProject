package com.dto;

// review를 불러올 때 별점, 작성자 닉네임까지 한번의 sql로 받아오기 위해 만든 DTO
public class ReviewDTO {
	private Long postId;
	private String postBoard;
	private String userId;
	private Long contId;
	private String postTitle;
	private String postDate;
	private String editDate;
	private String postText;
	private String score;  //별점
	private String nickName;  //닉네임
	
	public ReviewDTO() {
		super();
	}
	
	public ReviewDTO(Long postId, String postBoard, String userId, Long contId, String postTitle, String postDate,
			String editDate, String postText, String score, String nickName) {
		super();
		this.postId = postId;
		this.postBoard = postBoard;
		this.userId = userId;
		this.contId = contId;
		this.postTitle = postTitle;
		this.postDate = postDate;
		this.editDate = editDate;
		this.postText = postText;
		this.score = score;
		this.nickName = nickName;
	}
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
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getEditDate() {
		return editDate;
	}
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}
	public String getPostText() {
		return postText;
	}
	public void setPostText(String postText) {
		this.postText = postText;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "PostDTO [postId=" + postId + ", postBoard=" + postBoard + ", userId=" + userId + ", contId=" + contId
				+ ", postTitle=" + postTitle + ", postDate=" + postDate + ", editDate=" + editDate + ", postText="
				+ postText + ", score=" + score + ", nickName=" + nickName + "]";
	}
	
}
	