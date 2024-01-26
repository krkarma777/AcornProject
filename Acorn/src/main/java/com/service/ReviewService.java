package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.ReviewDAO;
import com.dto.CommentDTO;
import com.dto.ContentDTO;
import com.dto.ReviewDTO;
import com.dto.RateDTO;

public class ReviewService {
	
	ReviewDAO dao;
	
	public ReviewService() {
		super();
		dao = new ReviewDAO();
	}

	public ReviewDTO writeReview(ReviewDTO review) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			review = dao.writeReview(session, review);
			session.commit();
		} finally {
			session.close();
		}
		
		return review;
	}

	public ReviewDTO SelectReviewByUser(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		ReviewDTO review = null;
		try {
			review = dao.SelectReviewByUser(session, map);
		} finally {
			session.close();
		}
		
		return review;
	}

	public void UpdateScore(RateDTO dto) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			dao.UpdateScore(session, dto);
			session.commit();
		} finally {
			session.close();
		}
	}

	public List<ReviewDTO> selectReviews(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<ReviewDTO> reviewList = null;
		try {
			reviewList = dao.selectReviews(session, map);
		} finally {
			session.close();
		}
		
		return reviewList;
	}

	public ContentDTO selectContent(String contId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		ContentDTO content = null;
		try {
			content = dao.selectContent(session, contId);
		} finally {
			session.close();
		}
		
		return content;
	}

	public void UpdateLike(HashMap<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		try {
			dao.UpdateLike(session, map);
			session.commit();
		} finally {
			session.close();
		}
	}

	public ReviewDTO selectReviewByPostId(String postId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		ReviewDTO review = null;
		try {
			review = dao.selectReviewByPostId(session, postId);
		} finally {
			session.close();
		}
		
		return review;
	}

	public List<CommentDTO> selectComments(String postId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<CommentDTO> comments = null;
		try {
			comments = dao.selectComments(session, postId);
		} finally {
			session.close();
		}
		
		return comments;
	}

	public List<RateDTO> selectRates(String contId) {
		SqlSession session = MySqlSessionFactory.getSqlSession();
		List<RateDTO> rateList = null;
		try {
			rateList = dao.selectRates(session, contId);
		} finally {
			session.close();
		}
		
		return rateList;
	}

}
