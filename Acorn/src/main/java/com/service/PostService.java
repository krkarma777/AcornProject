package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.PostDAO;
import com.dto.PageDTO;
import com.dto.PostDTO;
import com.dto.PostInfoDTO;

public class PostService {
	
	PostDAO dao;
	
	public PostService() {
		// PostDAO 객체를 생성하여 사용
		dao = new PostDAO();
	}
	
	// 글 추가
	public Long insertContent(PostDTO post) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			// 글 추가 메서드 호출
			dao.insertContent(session, post);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return post.getPostId();
	}
	
	public Long viewPlus(PostDTO post) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			// 글 추가 메서드 호출
			dao.insertContent(session, post);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return post.getPostId();
	}
	
	
    // 글 조회
    public PostDTO select(Long postId) {
        SqlSession session = MySqlSessionFactory.getSqlSession();
        try {
            // 글 조회 메서드 호출
            return dao.select(session, postId);
        } finally {
            try {
                session.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    
    public PageDTO<PostDTO> getPostsByPage(String board, int curPage, int perPage) {
        SqlSession session = null;
        try {
            session = MySqlSessionFactory.getSqlSession();
            return dao.selectByPage(session, board, curPage, perPage);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    // 모든 글 조회
    public List<PostDTO> selectAll(String board) {
        SqlSession session = MySqlSessionFactory.getSqlSession();
        List<PostDTO> list = null;
        try {
            // 모든 글 조회 메서드 호출
            list = dao.selectAll(session, board);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return list;
    }
    
    // 글 수정
    public void update(Long postId, String updatedTitle, String updatedContent) {
        SqlSession session = MySqlSessionFactory.getSqlSession();
        try {
            // 글 수정 메서드 호출
            dao.updateContent(session, postId, updatedTitle, updatedContent);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    // 글 삭제
    public void delete(Long postId) {
        SqlSession session = MySqlSessionFactory.getSqlSession();
        try {
            // 글 삭제 메서드 호출
            dao.delete(session, postId);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback(); // 오류 발생 시 롤백
        } finally {
            try {
                session.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    
    //게시물 조회수 업데이트
	public int updateViewNum(Long postId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		
		int n = 0;
		
		try {
			n = dao.updateViewNum(session, postId);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return n;
		
	}//end updateVieNum
    
}
