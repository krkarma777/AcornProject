package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.PostDAO;
import com.dto.MemberDTO;
import com.dto.board.LikeDTO;
import com.dto.board.PageDTO;
import com.dto.board.PostDTO;
import com.dto.board.PostPageDTO;
import com.dto.board.PostSaveDTO;

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

	public PageDTO<PostPageDTO> getPostsByPage(HashMap<String, Object> map) {
		SqlSession session = null;
		try {
			session = MySqlSessionFactory.getSqlSession();
			return dao.selectByPage(session, map);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// 모든 글 조회
	public List<PostPageDTO> selectAll(HashMap<String, String> hashMap) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<PostPageDTO> list = null;
		try {
			// 모든 글 조회 메서드 호출
			list = dao.selectAll(session, hashMap);
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

	public List<PostPageDTO> popularPostTwoDays(HashMap<String, String> hashMap) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<PostPageDTO> list = null;
		try {
			// 모든 글 조회 메서드 호출
			list = dao.popularPostTwoDays(session, hashMap);
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
	public void update(Long postId, String updatedTitle, String updatedContent, Long postCategory) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			// 글 수정 메서드 호출
			dao.updateContent(session, postId, updatedTitle, updatedContent, postCategory);
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

	// 게시물 조회수 업데이트
	public int updateViewNum(Long postId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();

		int n = 0;

		try {
			n = dao.updateViewNum(session, postId);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return n;

	}// end updateVieNum

	public Long likeNum(Long postId) {
		Long like = 0L;
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			like = dao.likeNum(session, postId);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return like;
	}

	public Long viewNum(Long postId) {
		Long view = 0L;
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			view = dao.viewNum(session, postId);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return view;
	}

	public PostPageDTO selectPagePost(Long postId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		PostPageDTO dto = null;
		try {
			// 글 조회 메서드 호출
			dto = dao.selectPagePost(session, postId);
		} finally {
			try {
				session.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}

	public int postLike(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		int n = 0;
		try {
			// 글 추가 메서드 호출
			n = dao.postLike(session, map);
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
		return n;
	}

	public int updatePostLike(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			LikeDTO lDto = dao.selectPostLike(session, map);
			if (lDto != null) {
				dao.updatePostLike(session, map);
			} else {
				dao.postLike(session, map);
			}
			session.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
	}

	public MemberDTO selectMember(String userId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		MemberDTO member = null;
		try {
			member = dao.selectMember(session, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	// 임시글 insert
	public void insertPostSave(PostSaveDTO dto) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			dao.insertPostSave(session, dto);
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
	}//

	// 임시저장글 select
	public List<PostSaveDTO> postSaveSelect(String userId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<PostSaveDTO> list = null;
		try {
			list = dao.postSaveSelect(session, userId);
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
	}//

}
