package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.board.MessageDTO;

public class MessageDAO {

	public MessageDTO insert(SqlSession session, HashMap<String, String> map) {
		int n = session.insert("MessageMapper.insertMessage",map);
		return null;
	}

	public List<MessageDTO> selectSendMessage(SqlSession session, String senderId) {
		List<MessageDTO> sList=session.selectList("MessageMapper.selectSendMessage", senderId);
		return sList;
	}

	public List<MessageDTO> selectReceiveMessage(SqlSession session, String receiverId) {
		List<MessageDTO> rList=session.selectList("MessageMapper.selectReceiveMessage", receiverId);
		return rList;
	}

	

}
