package com.dto;

public class MemberDTO {
	private String userId;
	private String userPw;
	private String userName;
	private Integer userSSN1;
	private Integer userSSN2;
	private String userGender;
	private String nickName;
	private String userPhoneNum1;
	private String userPhoneNum2;
	private String userPhoneNum3;
	private String userEmailId;
	private String userEmailDomain;
	private String userSignDate;
	private String userType;
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDTO(String userId, String userPw, String userName, Integer userSSN1, Integer userSSN2,
			String userGender, String nickName, String userPhoneNum1, String userPhoneNum2, String userPhoneNum3,
			String userEmailId, String userEmailDomain, String userSignDate, String userType) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userSSN1 = userSSN1;
		this.userSSN2 = userSSN2;
		this.userGender = userGender;
		this.nickName = nickName;
		this.userPhoneNum1 = userPhoneNum1;
		this.userPhoneNum2 = userPhoneNum2;
		this.userPhoneNum3 = userPhoneNum3;
		this.userEmailId = userEmailId;
		this.userEmailDomain = userEmailDomain;
		this.userSignDate = userSignDate;
		this.userType = userType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserSSN1() {
		return userSSN1;
	}
	public void setUserSSN1(Integer userSSN1) {
		this.userSSN1 = userSSN1;
	}
	public Integer getUserSSN2() {
		return userSSN2;
	}
	public void setUserSSN2(Integer userSSN2) {
		this.userSSN2 = userSSN2;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserPhoneNum1() {
		return userPhoneNum1;
	}
	public void setUserPhoneNum1(String userPhoneNum1) {
		this.userPhoneNum1 = userPhoneNum1;
	}
	public String getUserPhoneNum2() {
		return userPhoneNum2;
	}
	public void setUserPhoneNum2(String userPhoneNum2) {
		this.userPhoneNum2 = userPhoneNum2;
	}
	public String getUserPhoneNum3() {
		return userPhoneNum3;
	}
	public void setUserPhoneNum3(String userPhoneNum3) {
		this.userPhoneNum3 = userPhoneNum3;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getUserEmailDomain() {
		return userEmailDomain;
	}
	public void setUserEmailDomain(String userEmailDomain) {
		this.userEmailDomain = userEmailDomain;
	}
	public String getUserSignDate() {
		return userSignDate;
	}
	public void setUserSignDate(String userSignDate) {
		this.userSignDate = userSignDate;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userSSN1="
				+ userSSN1 + ", userSSN2=" + userSSN2 + ", userGender=" + userGender + ", nickName=" + nickName
				+ ", userPhoneNum1=" + userPhoneNum1 + ", userPhoneNum2=" + userPhoneNum2 + ", userPhoneNum3="
				+ userPhoneNum3 + ", userEmailId=" + userEmailId + ", userEmailDomain=" + userEmailDomain
				+ ", userSignDate=" + userSignDate + ", userType=" + userType + "]";
	}
	
}
