package com.controller.board;

import java.util.List;
import java.util.Map;

import com.controller.board.util.BoardController;
import com.dto.board.PageDTO;
import com.dto.board.PostPageDTO;
import com.service.PostService;

public class BoardViewController implements BoardController {
    
    private String postBoard; // 게시판 이름 필드 추가

    public BoardViewController(String postBoard) {
        this.postBoard = postBoard;
    }

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // 현재 페이지 번호 설정
        String curPageStr = paramMap.get("curPage");
        int curPage = 1; // 기본값
        if (curPageStr != null && !curPageStr.isEmpty()) {
            curPage = Integer.parseInt(curPageStr);
        }
        // 페이지당 게시글 수 설정
        int perPage = 20;

        PostService service = new PostService();
        PageDTO<PostPageDTO> pageDTO = service.getPostsByPage(postBoard, curPage, perPage);

        List<PostPageDTO> list = pageDTO.getList();
        System.out.println(list);
        
        model.put("pDTO", pageDTO); // 페이지 정보를 모델에 추가
        model.put("postBoard", postBoard); // 게시판 이름을 모델에 추가

        return "board/boardView"; // 게시판 뷰 페이지의 이름을 반환
    }
}
