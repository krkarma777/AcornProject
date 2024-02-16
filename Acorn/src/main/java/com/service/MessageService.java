package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.MessageDAO;
import com.dto.board.MessageDTO;

public class MessageService {
	
	MessageDAO dao = new MessageDAO();

	public void insert(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		
		try {
			MessageDAO dao = new MessageDAO();
			dao.insert(session,map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.commit();
			session.close();
		}
		
	}

	public List<MessageDTO> selectSendMessage(String senderId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<MessageDTO> sList = null;
		try {
			MessageDAO dao = new MessageDAO();
			sList=dao.selectSendMessage(session,senderId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sList;
	}

	public List<MessageDTO> selectReceiveMessage(String receiverId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<MessageDTO> rList = null;
		try {
			MessageDAO dao = new MessageDAO();
			rList = dao.selectReceiveMessage(session,receiverId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return rList;
	}
	
	
	
	

}
