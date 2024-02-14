package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.CommentDTO;

public class CommentDAO {

	
	
	public CommentDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int  AddCommnet(CommentDTO commentDB, SqlSession s) {
		
		int recordCount = s.insert("CommentMapper", commentDB);
		return recordCount;
		
	}

	public List<CommentDTO> selectAll(SqlSession s){
		
		List<CommentDTO> commentDB = s.selectList("SelectAll");
		return commentDB;
		
	}
	
	public int selectOne(CommentDTO commentDB, SqlSession s) {
		int comid = s.selectOne("selectOne", commentDB);
		return comid;
	}
	
	
	public int deleteComment(SqlSession s, int comId){ 
		
		int num = s.delete("deleteComment", comId);
		return num;
	}
	
	public int deleteUpdateComment(SqlSession s, int comId){ 
		
		int num = s.update("deleteUpdateComment", comId);
		return num;
	}

	

	
	public int updateComment(SqlSession s, HashMap<String, String> map){ 
			
			int num = s.update("updateComment", map);
			return num;
		}
	
	

	public List<CommentDTO> selectAllByPostId(SqlSession session, Long postId) {
		return session.selectList("CommentMapper.selectAllByPostId", postId);
	}



	public List<CommentDTO> replyComSelectAllBycomId(SqlSession session, String comId) {
		return session.selectList("replyComSelectAllBycomId",comId);
	}
}
