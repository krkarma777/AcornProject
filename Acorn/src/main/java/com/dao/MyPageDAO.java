package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dto.CommentDTO;
import com.dto.MemberDTO;
import com.dto.ReviewDTO;

public class MyPageDAO {
	
	//회원정보 조회
	public MemberDTO mypage(SqlSession session,String userId) {
		   MemberDTO n = session.selectOne("MyPageMapper.mypage", userId);
		   return n;
	   }
//갱신된 회원 조회
	
	 public int updateMember(SqlSession session, MemberDTO dto) {
			
		 int n = session.insert("MyPageMapper.updateMember", dto);
		   return n;
		}
	//비밀번호 변경
//	 public int updatePwdMember(SqlSession session, HashMap<String,Object> map) {
//			
//			int n = session.update("MyPageMapper.updatePwdMember", map);
//			return n;
//		}
	//회원 탈퇴

//	 public int deleteMember(SqlSession session, Map<String, Object> paramMap) {
//	        return session.delete("MyPageMapper.deleteMember", paramMap);
//	    }
	public List<ReviewDTO> selectMyReview(SqlSession session, String userId) {
		List<ReviewDTO> list = 
				   session.selectList("MyPageMapper.selectMyReview", userId);
		   return list;
	}
	public List<CommentDTO> selectMyComm(SqlSession session, String userId) {
		List<CommentDTO> list = 
				   session.selectList("MyPageMapper.selectMyComm", userId);
		   return list;
	}
	
	   public int idCheck(SqlSession session, String userId) {
			int count = session.selectOne("MyPageMapper.idCheck", userId);
			return count;
		}

	public int commDel(SqlSession session, int comid) {
		int n = session.delete("MyPageMapper.commDel", comid);
		return n;
	}

	public int reviewDel(SqlSession session, long postId) {
		int n = session.delete("MyPageMapper.reviewDel", postId);
		return n;
	}

	
}
