package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dto.PageDTO;
import com.dto.PostDTO;
import com.dto.PostInfoDTO;

public class PostDAO {
    
    /**
     * 게시물 내용을 삽입합니다.
     * 
     * @param post    삽입할 게시물 정보를 담은 PostDTO 객체
     */
    public void insertContent(SqlSession session, PostDTO post) {
        session.insert("insertContent", post);
    }
    
    /**
     * 주어진 게시물 ID에 해당하는 게시물을 조회합니다.
     * 
     * @param postId  조회할 게시물의 ID
     * @return        조회된 게시물 정보를 담은 PostDTO 객체
     */
    public PostDTO select(SqlSession session, Long postId) {
        return session.selectOne("selectPost", postId);
    }
    
    /**
     * 주어진 게시물 ID에 해당하는 게시물을 삭제합니다.
     * 
     * @param postId  삭제할 게시물의 ID
     */
    public void delete(SqlSession session, Long postId) {
        session.delete("deletePostInfo", postId);
        session.delete("deletePost", postId);
    }
    
    /**
     * 주어진 게시판(board)에 속하는 모든 게시물 목록을 조회합니다.
     * 
     * @param board   조회할 게시물 목록이 속하는 게시판
     * @return        조회된 게시물 목록을 담은 List<PostDTO> 객체
     */
    public List<PostDTO> selectAll(SqlSession session, String board) {
        Map<String, Object> map = new HashMap<>();
        map.put("board", board);
        return session.selectList("selectAllPosts", map);
    }
    
    public PageDTO<PostDTO> selectByPage(SqlSession session, String board, int curPage, int perPage) {
        int offset = (curPage - 1) * perPage;
        List<PostDTO> list = session.selectList("selectAllByPage", 
                                                new HashMap<String, Object>() {{
                                                    put("board", board);
                                                    put("offset", offset);
                                                    put("perPage", perPage);
                                                }});

        int totalCount = session.selectOne("countPosts", board);

        PageDTO<PostDTO> pageDTO = new PageDTO<>();
        pageDTO.setList(list);
        pageDTO.setCurPage(curPage);
        pageDTO.setPerPage(perPage);
        pageDTO.setTotalCount(totalCount);

        return pageDTO;
    }
    
    /**
     * 주어진 게시물 ID에 해당하는 게시물의 제목과 내용을 업데이트합니다.
     * 
     * @param postId         업데이트할 게시물의 ID
     * @param updatedTitle   업데이트할 게시물 제목
     * @param updatedContent 업데이트할 게시물 내용
     */
    public void updateContent(SqlSession session, Long postId, String updatedTitle, String updatedContent) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("postId", postId);
        paramMap.put("updatedTitle", updatedTitle);
        paramMap.put("updatedContent", updatedContent);

        session.update("updateContent", paramMap);
    }

    
    /**
     * 주어진 게시물 ID에 해당하는 게시물의 조회수를 업데이트합니다.
     * 
     * @param postId         업데이트할 게시물 정보
     */
	public int updateViewNum(SqlSession session, Long postId) {
		
		int n = session.update("updateViewNum", postId);
		
		return n;

	}
}//end class
