package com.dao;



import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.AdminRprtdDTO;

public class AdminReportDAO {

	

	public List<AdminRprtdDTO> SearchReport(SqlSession session, HashMap<String, String> map) {
		
		System.out.println(map);
		List<AdminRprtdDTO> list = session.selectList("AdminReportMapper.SearchPost", map);
		System.out.println("in AdminReportDAO");
		System.out.println(list);
		return list;
	}

	public List<AdminRprtdDTO> ReportedMemList(SqlSession session, HashMap<String, String> map) {
		System.out.println("in dao map:" + map);
		List<AdminRprtdDTO>list = session.selectList("AdminReportMapper.ReportedMemberList", map);
		
		System.out.println("in dao :" + list);
		return list;
	}

	public int delReportedPost(SqlSession session, List<String> list) {
		
		int n = session.delete("AdminReportMapper.delReportedPost", list);
		return n;
	}

	
}
