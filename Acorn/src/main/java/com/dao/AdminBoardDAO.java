package com.dao;



import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.BoardAdminDTO;

public class AdminBoardDAO {

	

	public List<BoardAdminDTO> SearchPost(SqlSession session, HashMap<String, String> map) {
		
		System.out.println(map);
		List<BoardAdminDTO> list = session.selectList("BoardMapper.SearchPost", map);
		System.out.println("in boardDAO" + list);
		return list;
	}

	
}
