package com.dto;

public class PostInfoDTO {
	private long postId;
	private long viewNum;
	private long likeNum;
	
	//기본생성자
	public PostInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// 사용자 지정 생성자
	public PostInfoDTO(long postId, long viewNum, long likeNum) {
		super();
		this.postId = postId;
		this.viewNum = viewNum;
		this.likeNum = likeNum;
	}
	
	//get, set
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getViewNum() {
		return viewNum;
	}
	public void setViewNum(long viewNum) {
		this.viewNum = viewNum;
	}
	public long getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(long likeNum) {
		this.likeNum = likeNum;
	}
	
	@Override
	public String toString() {
		return "PostInfoDTO [postId=" + postId + ", viewNum=" + viewNum + ", likeNum=" + likeNum + "]";
	}
	
}//end class
