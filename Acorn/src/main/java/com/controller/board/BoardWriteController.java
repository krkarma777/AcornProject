package com.controller.board;

import java.util.Map;

import com.controller.board.util.BoardController;
import com.dto.PostDTO;
import com.service.PostService;

public class BoardWriteController implements BoardController {

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {

		// 폼에서 전송된 데이터를 파싱
		String userId = paramMap.get("userId");
		String postTitle = paramMap.get("postTitle");
		String postText = paramMap.get("postText");
		String postBoard = paramMap.get("bn"); //게시판 이름 파라미터


		// 글 작성 페이지로 이동
		if (userId == null) {
			return "board/post";
		}

		// PostDTO 객체 생성 및 데이터 설정
		PostDTO post = new PostDTO(userId, postTitle, postText, postBoard);

		// PostService를 통해 글 작성 요청 처리
		PostService service = new PostService();
		Long postId = service.insertContent(post); // insertContent 메서드에서 자동 생성된 postId를 반환
		
		// 작성된 글로 리다이렉트
		String redirectURL = String.format("/Acorn/board/content?postId=%d&bn=%s", postId, postBoard);
		return "redirect:" + redirectURL;
	}
}
