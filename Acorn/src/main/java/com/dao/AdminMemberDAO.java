package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.MemberAdminDTO;

public class AdminMemberDAO {

	public List<MemberAdminDTO> SearchMember(SqlSession session, String SearchValue) {
		SearchValue = "%" + SearchValue + "%";
		List<MemberAdminDTO> list = session.selectList("MemberMapper.SearchMember", SearchValue);
		return list;
	}

}
