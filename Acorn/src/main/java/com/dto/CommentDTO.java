package com.dto;

public class CommentDTO {
	
	private int comId;
	private Long postId;
	private String userId;
	private String comDate;
	private String comText;
	private int aboveCom;
	
	

	public CommentDTO() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "CommentDTO [comId=" + comId + ", postId=" + postId + ", userId=" + userId + ", comDate=" + comDate
				+ ", comText=" + comText + ", aboveCom=" + aboveCom + "]";
	}



	public CommentDTO(int comId, Long postId, String userId, String comDate, String comText, int aboveCom) {
		super();
		this.comId = comId;
		this.postId = postId;
		this.userId = userId;
		this.comDate = comDate;
		this.comText = comText;
		this.aboveCom = aboveCom;
	}



	public int getComId() {
		return comId;
	}



	public void setComId(int comId) {
		this.comId = comId;
	}



	public Long getPostId() {
		return postId;
	}



	public void setPostId(Long postId) {
		this.postId = postId;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getComDate() {
		return comDate;
	}



	public void setComDate(String comDate) {
		this.comDate = comDate;
	}



	public String getComText() {
		return comText;
	}



	public void setComText(String comText) {
		this.comText = comText;
	}



	public int getAboveCom() {
		return aboveCom;
	}



	public void setAboveCom(int aboveCom) {
		this.aboveCom = aboveCom;
	}



	
	
	
	
}
