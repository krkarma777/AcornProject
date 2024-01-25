package adminpage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import adminpage.dto.MemberDTO;

public class MemberDAO {

	public List<MemberDTO> SearchMember(SqlSession session, String SearchValue) {
		SearchValue = "%" + SearchValue + "%";
		List<MemberDTO> list = session.selectList("MemberMapper.SearchMember", SearchValue);
		return list;
	}

}
