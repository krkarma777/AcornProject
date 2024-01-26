package com.service.adminpage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.AdminMemberDAO;
import com.dto.AdminMemberDTO;

public class AdminMemberService {
	
	AdminMemberDAO dao = new AdminMemberDAO();

	public List<AdminMemberDTO> SearchMember(String SearchValue) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<AdminMemberDTO> list = null;
		try {
			list = dao.SearchMember(session, SearchValue);
		}finally {
			session.close();
		}
		
		return list;
	}
	
}
