package com.web.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.domain.dao.PostDAO;
import com.domain.dto.PageDTO;
import com.domain.dto.PostDTO;

public class PostService {
	
	PostDAO dao;
	
	public PostService() {
		// 스프링 전환시 의존성 주입 예정
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
        } finally {
            try {
                session.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
