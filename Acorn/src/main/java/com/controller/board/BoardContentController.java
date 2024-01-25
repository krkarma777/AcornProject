package com.controller.board;

import java.util.Map;

import com.controller.board.util.AuthUtils;
import com.controller.board.util.BoardController;
import com.controller.board.util.ErrorMessage;
import com.dto.board.PostPageDTO;
import com.service.PostService;

public class BoardContentController implements BoardController {

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {

		// 파라미터로부터 게시글의 고유 ID인 postId를 추출합니다.
		Long postId = Long.parseLong(paramMap.get("postId"));

		// PostService를 이용하여 postId에 해당하는 게시글을 검색합니다.
		PostService service = new PostService();
		PostPageDTO post = service.selectPagePost(postId);
		
		if(!AuthUtils.isUserAuthorized(post, paramMap) ) {
			model.put("mismatchError", ErrorMessage.MISMATCH_ERROR.getMessage());
		}

		// 검색된 게시글 정보를 모델에 추가합니다. 이후 뷰 페이지에서 이 정보를 사용할 수 있습니다.
		model.put("postText", post.getPostText()); // 게시글 내용
		model.put("postTitle", post.getPostTitle()); // 게시글 제목
		model.put("postDate", post.getPostDate()); // 게시글 작성일
		model.put("nickname", post.getNickname()); // 게시글 작성자의 사용자 닉네임
		model.put("viewNum", post.getViewNum()); // 조회수
		model.put("likeNum", post.getLikeNum()); // 좋아요 갯수
		model.put("commentCount", post.getCommentCount()); // 댓글 갯수
		
		
		//게시물 조회수 업데이트
		service.updateViewNum(postId);
		
		// "content" 라는 뷰 페이지의 이름을 반환하여 해당 뷰 페이지를 표시합니다.
		return "board/content";
	}
}
