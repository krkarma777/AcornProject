package adminpage.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;

import adminpage.dao.BoardDAO;
import adminpage.dto.BoardDTO;

public class BoardService {
	
	BoardDAO dao = new BoardDAO();

	

	public List<BoardDTO> SearchPost(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<BoardDTO> list = null;
		
		try {
			list = dao.SearchPost(session, map);
		}finally {
			session.close();
		}
		return list;
	}

}
