package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.board.ScrapDTO;

public class ScrapDAO {

	public int insert(SqlSession session, HashMap<String, String> map) {
		int n = session.insert("ScrapMapper.insert",map);
		return n;
	}

	public List<ScrapDTO> checkScrap(SqlSession session, HashMap<String, String> map) {
		List<ScrapDTO> dto = session.selectList("ScrapMapper.checkScrap",map);
		return dto;
	}

}
