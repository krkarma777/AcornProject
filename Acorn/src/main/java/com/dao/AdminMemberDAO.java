package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.AdminMemberDTO;

public class AdminMemberDAO {

	public List<AdminMemberDTO> SearchMember(SqlSession session, HashMap<String, String> map) {
		List<AdminMemberDTO> list = session.selectList("AdminMemberMapper.SearchMember", map);
		return list;
	}

}
