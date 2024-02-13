package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dto.MemberDTO;
import com.dto.board.LikeDTO;
import com.dto.board.PageDTO;
import com.dto.board.PostDTO;
import com.dto.board.PostPageDTO;
import com.dto.board.PostSaveDTO;

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
     * @param hashMap   조회할 게시물 목록이 속하는 게시판
     * @return        조회된 게시물 목록을 담은 List<PostDTO> 객체
     */
    public List<PostPageDTO> selectAll(SqlSession session, HashMap<String, String> hashMap) {
        return session.selectList("selectAllPosts", hashMap);
    }
    
    public PageDTO<PostPageDTO> selectByPage(SqlSession session, HashMap<String, Object> map) {
        List<PostPageDTO> list = session.selectList("selectAllByPage", map);
        
        int curPage = (int)map.get("curPage");
        int perPage = (int)map.get("perPage");
        String postBoard = (String)map.get("postBoard");
        
        int totalCount = session.selectOne("countPosts", map);

        PageDTO<PostPageDTO> pageDTO = new PageDTO<>();
        
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
    public void updateContent(SqlSession session, Long postId, String updatedTitle, String updatedContent, Long postCategory) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("postId", postId);
        paramMap.put("updatedTitle", updatedTitle);
        paramMap.put("updatedContent", updatedContent);
        paramMap.put("postCategory", postCategory);

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
	
	public List<PostPageDTO> popularPostTwoDays(SqlSession session, HashMap<String, String> map) {
		return session.selectList("popularPostTwoDays", map);
	}

	public Long likeNum(SqlSession session, Long postId) {
		return session.selectOne("likeNum",postId);
	}

	public Long viewNum(SqlSession session, Long postId) {
		return session.selectOne("viewNum",postId);
	}

	public PostPageDTO selectPagePost(SqlSession session, Long postId) {
		return session.selectOne("selectPagePost",postId);
	}

	public int postLike(SqlSession session, HashMap<String, String> map) {
		return session.insert("postLike",map);
	}

	public LikeDTO selectPostLike(SqlSession session, HashMap<String, String> map) {
		LikeDTO lDto = session.selectOne("selectPostLike",map);
		return lDto;
	}

	public int updatePostLike(SqlSession session, HashMap<String, String> map) {
		 int num = session.update("updatePostLike",map);
		 return num;
		
	}

	public MemberDTO selectMember(SqlSession session, String userId) {
		MemberDTO member = session.selectOne("selectMember",userId);
		return member;
	}
	
	public int insertPostSave(SqlSession session, PostSaveDTO dto){
		
		return session.insert("insertPostSave", dto);
	}

	public List<PostSaveDTO> postSaveSelect(SqlSession session, String userId) {
		
		return session.selectList("postSaveSelect", userId);
	}

	public void deletePostSave(SqlSession session, String postSaveId) {
		session.delete("deletePostSave", postSaveId);
	}
	
	
}//end class
