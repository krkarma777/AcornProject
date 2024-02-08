package com.controller.board;

import java.util.List;
import java.util.Map;

import com.controller.board.util.AuthUtils;
import com.controller.board.util.BoardController;
import com.dto.board.PostDTO;
import com.dto.board.PostSaveDTO;
import com.service.PostService;

public class BoardWriteController implements BoardController {
    
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 로그인 유저만 접속
        if (!AuthUtils.isUserLoggedIn(paramMap)) {
            return "redirect:/Acorn/Login";
        }

        // 폼에서 전송된 데이터를 파싱
        String postTitle = paramMap.get("postTitle");
        String userId = paramMap.get("userId");
        model.put("userId", userId);
        PostService service = new PostService();
		List<PostSaveDTO> postSaveList = service.postSaveSelect(userId);
		model.put("postSaveList", postSaveList);
        // 처음 접속시 post.jsp로 리다이렉트
        if (postTitle == null) {
            return "board/post";
        }

        // PostDTO 객체 생성 및 데이터 설정
        PostDTO post = createPostDTO(paramMap);
        // PostService를 통해 글 작성 요청 처리

        Long postId = service.insertContent(post); // insertContent 메서드에서 자동 생성된 postId를 반환
        // 작성된 글로 리다이렉트
        String postBoard = paramMap.get("bn"); // 게시판 이름 파라미터
        String redirectURL = String.format("/Acorn/board/content?postId=%d&bn=%s", postId, postBoard);
        return "redirect:" + redirectURL;
    }

    private PostDTO createPostDTO(Map<String, String> paramMap) {
        PostDTO post = new PostDTO();
        post.setUserId(paramMap.get("userId"));
        post.setNickname(paramMap.get("nickname"));
        post.setPostTitle(paramMap.get("postTitle"));
        post.setPostText(paramMap.get("postText"));
        post.setPostBoard(paramMap.get("bn"));
        post.setCategoryId(Long.parseLong(paramMap.get("postCategory")));
        return post;
    }
}
