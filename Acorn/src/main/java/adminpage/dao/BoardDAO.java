package adminpage.dao;



import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import adminpage.dto.BoardDTO;

public class BoardDAO {

	

	public List<BoardDTO> SearchPost(SqlSession session, HashMap<String, String> map) {
		
		System.out.println(map);
		List<BoardDTO> list = session.selectList("BoardMapper.SearchPost", map);
		System.out.println("in boardDAO" + list);
		return list;
	}

	
}
