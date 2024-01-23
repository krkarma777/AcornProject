package com.dto;

public class PostInfoDTO {
	private Long postId;
	private Long viewNum;
	private Long likeNum;
	public PostInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostInfoDTO(Long postId, Long viewNum, Long likeNum) {
		super();
		this.postId = postId;
		this.viewNum = viewNum;
		this.likeNum = likeNum;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
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
		return "PostInfoDTO [postId=" + postId + ", viewNum=" + viewNum + ", likeNum=" + likeNum + "]";
	}
	
}
