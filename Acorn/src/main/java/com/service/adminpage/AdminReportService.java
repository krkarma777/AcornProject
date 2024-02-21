package com.service.adminpage;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.AdminReportDAO;
import com.dto.AdminRprtdDTO;

public class AdminReportService {
	
	AdminReportDAO dao = new AdminReportDAO();

	public List<AdminRprtdDTO> SearchReport(HashMap<String, String> map) {
		
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<AdminRprtdDTO> list = null;
		
		try {
			list = dao.SearchReport(session, map);
		}finally {
			session.close();
		}
		return list;
	}

	public List<AdminRprtdDTO> ReportedMemList(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<AdminRprtdDTO> list = null;
		try {
			list = dao.ReportedMemList(session, map);
		}finally {
			session.close();
		}
		return list;
	}

	public int delReportedPost(List<String> list) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		int n = 0;
		try {
			n = dao.delReportedPost(session, list);
			session.commit();
		}finally {
			session.close();
		}
		return n;
	}

}
