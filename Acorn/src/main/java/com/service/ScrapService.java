package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.ScrapDAO;
import com.dto.board.ScrapDTO;

public class ScrapService {

	public void insert(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			ScrapDAO dao = new ScrapDAO();
			dao.insert(session,map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.commit();
			session.close();
		}
	}

	public List<ScrapDTO> checkScrap(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<ScrapDTO> dto = null;
		try {
			ScrapDAO dao = new ScrapDAO();
			dto = dao.checkScrap(session,map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return dto;
	}



	

}
