package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.AdminBoardDAO;
import com.dto.BoardAdminDTO;

public class AdminBoardService {
	
	AdminBoardDAO dao = new AdminBoardDAO();

	

	public List<BoardAdminDTO> SearchPost(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<BoardAdminDTO> list = null;
		
		try {
			list = dao.SearchPost(session, map);
		}finally {
			session.close();
		}
		return list;
	}

}
