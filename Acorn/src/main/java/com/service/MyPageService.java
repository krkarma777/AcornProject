package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;

import com.dao.MemberDAO;
import com.dao.MyPageDAO;
import com.dto.CommentDTO;
import com.dto.MemberDTO;
import com.dto.ReviewDTO;

public class MyPageService {


	//회원 조회
	 public MemberDTO mypage(String userId) {
			SqlSession session = MySqlSessionFactory.getSqlSession();
			MemberDTO dto = null;
			try {
				 MyPageDAO dao = new MyPageDAO();
				 dto = dao.mypage(session, userId);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				session.close();
			}
			return dto;
		}//end idCheck
	 public int updateMember(MemberDTO dto) {
		  
		  SqlSession session = MySqlSessionFactory.getSqlSession();
		  int n = 0;
		  try {
			  MyPageDAO dao = new MyPageDAO();
			  n = dao.updateMember(session, dto);
			  session.commit();
		  }finally {
			session.close();
		}
		  return n;
	   }//end memberUpdate
	
//	public int updatePwdMember(HashMap<String,Object> map) {
//		 SqlSession session = MySqlSessionFactory.getSqlSession();
//		int n = 0;
//		 try {
//	      n = dao.updatePwdMember(session,map);
//	    	 session.commit();
//	      }finally {
//			session.close();
//	      }
//	      return n;
//		}//end select
//	
//	public int deleteMember(String userid, String userpw) {
//	    SqlSession session = MySqlSessionFactory.getSqlSession();
//	    int n = 0;
//	    try {
//	        Map<String, Object> paramMap = new HashMap<>();
//	        paramMap.put("userid", userid);
//	        paramMap.put("userpw", userpw);
//	        n = dao.deleteMember(session, paramMap);
//	        session.commit();
//	    } finally {
//	        session.close();
//	    }
//	    return n;
//	}
//	public List<MemberDTO> select(String userid) {
//		  SqlSession session = MySqlSessionFactory.getSqlSession();
//		  List<MemberDTO> list = null;
//		  try {
//			  MyPageDAO dao = new MyPageDAO();
//			  list = dao.select(session, userid);
//			  session.commit();
//		  }finally {
//			session.close();
//		}
//		  return list;
//	   }//end memberAdd
	
	//myreview select
	public List<ReviewDTO> selectMyReview(String userId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<ReviewDTO> list = null;
		try {
			 MyPageDAO dao = new MyPageDAO();
			 list = dao.selectMyReview(session, userId);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}//end selectMyReview
	public List<CommentDTO> selectMyComm(String userId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<CommentDTO> list = null;
		try {
			 MyPageDAO dao = new MyPageDAO();
			 list = dao.selectMyComm(session, userId);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}//end selectMyComm

	 public int idCheck(String userId) {
			SqlSession session = MySqlSessionFactory.getSqlSession();
			int count = 0;
			try {
				 MyPageDAO dao = new MyPageDAO();
				count = dao.idCheck(session, userId);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				session.close();
			}
			return count;
		}//end idCheck
	public int commDel(int comId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		int n = 0;
		try {
			MyPageDAO dao = new MyPageDAO();
			n = dao.commDel(session, comId);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}
	public int reviewDel(long postId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		int n = 0;
		try {
			MyPageDAO dao = new MyPageDAO();
			n = dao.reviewDel(session, postId);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}
}
