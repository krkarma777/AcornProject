package com.dao;



import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.AdminBoardDTO;

public class AdminBoardDAO {

	

	public List<AdminBoardDTO> SearchPost(SqlSession session, HashMap<String, String> map) {
		
		System.out.println(map);
		List<AdminBoardDTO> list = session.selectList("BoardMapper.SearchPost", map);
		System.out.println("in boardDAO" + list);
		return list;
	}

	
}
