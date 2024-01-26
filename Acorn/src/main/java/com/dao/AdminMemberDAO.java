package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.AdminMemberDTO;

public class AdminMemberDAO {

	public List<AdminMemberDTO> SearchMember(SqlSession session, String SearchValue) {
		SearchValue = "%" + SearchValue + "%";
		List<AdminMemberDTO> list = session.selectList("MemberMapper.SearchMember", SearchValue);
		return list;
	}

}
