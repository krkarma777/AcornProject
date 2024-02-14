package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.CommentDAO;
import com.dto.CommentDTO;

public class CommentService {

	CommentDAO dao;

	public CommentService() {
		dao = new CommentDAO();
	}

	public int AddCommnet(CommentDTO commentDB) {

		int recordCount = 0;
		
		SqlSession s = MySqlSessionFactory.getSqlSession();

		try {
			String aboveComId = commentDB.getAboveComId();
			recordCount = dao.AddCommnet(commentDB, s);
			s.commit();
		} finally {
			if (s != null)
				s.close();
		}

		return recordCount;
	}

	public List<CommentDTO> selectAll() {

		List<CommentDTO> commentDB = null;
		SqlSession s = MySqlSessionFactory.getSqlSession();

		try {
			commentDB = dao.selectAll(s);
			s.commit();
		} finally {
			if (s != null)
				s.close();
		}

		return commentDB;
	}

	public int selectOne(CommentDTO commentDB) {

		int commid = 0;
		SqlSession s = MySqlSessionFactory.getSqlSession();

		try {
			commid = dao.selectOne(commentDB, s);
			s.commit();
		} finally {
			if (s != null)
				s.close();
		}

		return commid;
	}

	public int deleteComment(int comId) {
		int num = 0;
		SqlSession s = MySqlSessionFactory.getSqlSession();

		try {
			num = dao.deleteComment(s, comId);
			s.commit();
		} finally {
			if (s != null)
				s.close();
		}
		return num;

	}

	public int deleteUpdateComment(int comId) {
		int num = 0;
		SqlSession s = MySqlSessionFactory.getSqlSession();

		try {
			num = dao.deleteUpdateComment(s, comId);
			s.commit();
		} finally {
			if (s != null)
				s.close();
		}
		return num;

	}
	
	
	
	public int updateComment(HashMap<String, String> map) {
		int num = 0;
		SqlSession s = MySqlSessionFactory.getSqlSession();

		try {
			num = dao.updateComment(s, map);
			s.commit();
		} finally {
			if (s != null)
				s.close();
		}
		return num;

	}
	
	
	

	public List<CommentDTO> selectAllByPostId(Long postId) {
		List<CommentDTO> commentDB = null;
		SqlSession session = MySqlSessionFactory.getSqlSession();

		try {
			commentDB = dao.selectAllByPostId(session, postId);
			session.commit();
		} finally {
			if (session != null)
				session.close();
		}

		return commentDB;
	}
	
	
	
	
	public List<CommentDTO> replyComSelectAllBycomId(String comId){
		List<CommentDTO> replyCommentDB  = null;
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			replyCommentDB = dao.replyComSelectAllBycomId(session, comId);
			session.commit();
		} finally {
			if (session != null)
				session.close();
		}

		return replyCommentDB;
	}
	
	

}
