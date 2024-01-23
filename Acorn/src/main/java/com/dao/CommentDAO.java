package com.dao;

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
	
	
	public int deleteComment(SqlSession s, int comid){ 
		
		int num = s.delete("deleteComment", comid);
		return num;
	}
	
	public int updateComment(SqlSession s, int comid){ 
		
		int num = s.update("updateComment", comid);
		return num;
	}
}
