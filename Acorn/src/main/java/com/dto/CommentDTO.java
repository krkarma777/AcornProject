package com.dto;

public class CommentDTO {
	
	private int comid;
	private Long postid;
	private String userid;
	private String comdate;
	private String comtext;
	private int abovecom;
	//private int countnumber;
	
	

	public CommentDTO() {
		// TODO Auto-generated constructor stub
	}



	public CommentDTO(int comid, Long postid, String userid, String comdate, String comtext, int abovecom
			/*int countnumber*/) {
		super();
		this.comid = comid;
		this.postid = postid;
		this.userid = userid;
		this.comdate = comdate;
		this.comtext = comtext;
		this.abovecom = abovecom;
		//this.countnumber = countnumber;
	}



	public int getComid() {
		return comid;
	}



	public void setComid(int comid) {
		this.comid = comid;
	}



	public Long getPostid() {
		return postid;
	}



	public void setPostid(Long postid) {
		this.postid = postid;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getComdate() {
		return comdate;
	}



	public void setComdate(String comdate) {
		this.comdate = comdate;
	}



	public String getComtext() {
		return comtext;
	}



	public void setComtext(String comtext) {
		this.comtext = comtext;
	}



	public int getAbovecom() {
		return abovecom;
	}



	public void setAbovecom(int abovecom) {
		this.abovecom = abovecom;
	}



	/*
	 * public int getCountnumber() { return countnumber; }
	 * 
	 * 
	 * 
	 * public void setCountnumber(int countnumber) { this.countnumber = countnumber;
	 * }
	 */



	@Override
	public String toString() {
		return "CommentDB [comid=" + comid + ", postid=" + postid + ", userid=" + userid + ", comdate=" + comdate
				+ ", comtext=" + comtext + ", abovecom=" + abovecom + ", countnumber=" + /* countnumber */ "]";
	}




	
	
	
	
}
