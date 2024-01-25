package adminpage.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;

import adminpage.dao.MemberDAO;
import adminpage.dto.MemberDTO;

public class MemberService {
	
	MemberDAO dao = new MemberDAO();

	public List<MemberDTO> SearchMember(String SearchValue) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<MemberDTO> list = null;
		try {
			list = dao.SearchMember(session, SearchValue);
		}finally {
			session.close();
		}
		
		return list;
	}
	
}
