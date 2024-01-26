package com.service.adminpage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.AdminMemberDAO;
import com.dto.MemberAdminDTO;

public class AdminMemberService {
	
	AdminMemberDAO dao = new AdminMemberDAO();

	public List<MemberAdminDTO> SearchMember(String SearchValue) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<MemberAdminDTO> list = null;
		try {
			list = dao.SearchMember(session, SearchValue);
		}finally {
			session.close();
		}
		
		return list;
	}
	
}
