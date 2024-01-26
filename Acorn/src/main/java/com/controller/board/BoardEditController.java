package com.controller.board;

import java.util.Map;

import com.controller.board.util.AuthUtils;
import com.controller.board.util.BoardController;
import com.controller.board.util.ErrorMessage;
import com.dto.board.PostDTO;
import com.service.PostService;

public class BoardEditController implements BoardController {

	String INVALID_REQUEST = ErrorMessage.INVALID_REQUEST.getMessage();
	String ERROR_PAGE = ErrorMessage.ERROR_PAGE.getMessage();
	String ERROR = ErrorMessage.ERROR.getMessage();

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		// 이 메소드는 게시물 수정 요청을 처리합니다.

		// 로그인 여부 확인
		if (!AuthUtils.isUserLoggedIn(paramMap)) {
			return "redirect:/Acorn/Login";
		}
		// 사용자가 로그인했는지 확인하고, 로그인하지 않았으면 로그인 페이지로 리다이렉트합니다.

		try {
			// postId 파싱 및 검증
			Long postId = parseAndValidatePostId(paramMap.get("postId"));
			// 사용자가 전달한 'postId' 매개변수를 분석하고 유효한지 확인합니다.

			// 게시글 정보 조회
			PostDTO post = fetchPost(postId, model);
			if (post == null)
				return ERROR_PAGE;
			// 해당 ID의 게시글을 데이터베이스에서 조회합니다. 게시글이 없으면 에러 페이지로 이동합니다.

			// 사용자 권한 검증
			if (!AuthUtils.isUserAuthorized(post, paramMap))
				return ERROR_PAGE;
			// 게시글을 수정할 수 있는 사용자인지 확인합니다. 권한이 없으면 에러 페이지로 이동합니다.

			// 게시글 수정 또는 수정 페이지 로드 처리
			return processEditOrRedirect(postId, paramMap, model, post);
			// 게시글을 수정하거나 수정 페이지로 리다이렉트하는 로직을 처리합니다.
		} catch (NumberFormatException e) {
			return ERROR_PAGE;
			// postId가 올바른 숫자 형식이 아닐 때 에러 페이지로 리다이렉트합니다.
		}
	}

	// 이하는 각각의 도우미 메소드들입니다.
	// postId를 파싱하고 검증하는 메소드, 게시글을 가져오는 메소드, 사용자 권한을 확인하는 메소드,
	// 그리고 게시글을 수정하거나 수정 페이지로 리다이렉트하는 메소드가 정의되어 있습니다.
	private Long parseAndValidatePostId(String postIdParam) throws NumberFormatException {
		return Long.parseLong(postIdParam);
	}

	private PostDTO fetchPost(Long postId, Map<String, Object> model) {
		PostService service = new PostService();
		PostDTO post = service.select(postId);
		if (post == null) {
			model.put(ERROR, INVALID_REQUEST);
		}
		return post;
	}

	private String processEditOrRedirect(Long postId, Map<String, String> paramMap, Map<String, Object> model,
			PostDTO post) {
		String postTitle = paramMap.get("postTitle");
		String postText = paramMap.get("postText");
		String postBoard = paramMap.get("bn");

		if (postTitle == null || postText == null) {
			model.put("post", post);
			return "board/edit";
		} else {
			new PostService().update(postId, postTitle, postText);
			return String.format("redirect:/Acorn/board/content?postId=%d&bn=%s", postId, postBoard);
		}
	}
}
