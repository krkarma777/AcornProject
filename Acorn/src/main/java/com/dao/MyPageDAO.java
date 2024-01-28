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
	public MemberDTO mypage(SqlSession session,String userid) {
		   MemberDTO n = session.selectOne("MyPageMapper.mypage", userid);
		   return n;
	   }
//갱신된 회원 조회
	public List<MemberDTO> select(SqlSession session, String userid) {
		List<MemberDTO> list =  session.selectList("MyPageMapper.selectMember");
		return list;
	}
	//회원정보수정
	 public int updateMember(SqlSession session, MemberDTO dto) {
			
		 int n = session.insert("MyPageMapper.updateMember", dto);
		   return n;
		}
	//비밀번호 변경
	 public int updatePwdMember(SqlSession session, HashMap<String,Object> map) {
			
			int n = session.update("MyPageMapper.updatePwdMember", map);
			return n;
		}
	//회원 탈퇴

	 public int deleteMember(SqlSession session, Map<String, Object> paramMap) {
	        return session.delete("MyPageMapper.deleteMember", paramMap);
	    }
	public List<ReviewDTO> selectMyReview(SqlSession session, String userid) {
		List<ReviewDTO> list = 
				   session.selectList("MyPageMapper.selectMyReview", userid);
		   return list;
	}
	public List<CommentDTO> selectMyComm(SqlSession session, String userid) {
		List<CommentDTO> list = 
				   session.selectList("MyPageMapper.selectMyComm", userid);
		   return list;
	}
	
	   public int idCheck(SqlSession session, String userid) {
			int count = session.selectOne("MyPageMapper.idCheck", userid);
			return count;
		}

	
}
