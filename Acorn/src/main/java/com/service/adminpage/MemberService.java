package com.service.adminpage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.MemberDAO;
import com.dto.MemberAdminDTO;

public class MemberService {
	
	MemberDAO dao = new MemberDAO();

	public List<MemberAdminDTO> SearchMember(String SearchValue) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<MemberAdminDTO> list = null;
		try {
			list = dao.SearchMember(session, SearchValue);
		}finally {
			session.close();
		}
		
		return list;
	}
	
}
