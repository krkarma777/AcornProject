package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.apache.ibatis.session.SqlSession;

import com.dto.ContentDTO;
import com.dto.ReviewDTO;
import com.dto.RateDTO;

public class ReviewDAO {

	public ReviewDTO writeReview(SqlSession session, ReviewDTO review) {
		
		// 이미 작성한 리뷰가 있는지 검사
		int count = session.selectOne("check", review);
		int num = 0;
		if(count>0) {
			//update 갱신
			num = session.insert("updateReview", review);
		}else {
			//insert 최초생성
			num = session.insert("writeReview", review);
			//별점데이터 생성
			session.insert("createRateData", review);
		}
		
		// 변경된 값 반환
		Long postId = review.getPostId();
		review = session.selectOne("selectReview", postId);
		return review;
	}

	public ReviewDTO SelectReviewByUser(SqlSession session, HashMap<String, String> map) {
		ReviewDTO review = session.selectOne("selectReviewByUser", map);
		//Integer score = session.selectOne("selectScore", map);
		//HashMap<String, Object> result = new HashMap<String, Object>();
		//result.put("review", review);
		//result.put("score", score);
		return review;
	}

	public void UpdateScore(SqlSession session, RateDTO dto) {
		//System.out.println(dto);
		session.update("insertOrUpdateRating", dto);
	}

	public List<ReviewDTO> selectReviews(SqlSession session, String contId) {
		List<ReviewDTO> reviewList = session.selectList("selectReviews", contId);
		//System.out.println("dao " + reviewList + contId);
		return reviewList;
	}

	public ContentDTO selectContent(SqlSession session, String contId) {
		ContentDTO content = session.selectOne("selectContent", contId);
		return content;
	}

}
