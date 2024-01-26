package com.service.adminpage;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.AdminBoardDAO;
import com.dto.AdminBoardDTO;

public class AdminBoardService {
	
	AdminBoardDAO dao = new AdminBoardDAO();

	

	public List<AdminBoardDTO> SearchPost(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<AdminBoardDTO> list = null;
		
		try {
			list = dao.SearchPost(session, map);
		}finally {
			session.close();
		}
		return list;
	}

}
