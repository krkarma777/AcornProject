package com.controller.board;

import java.util.HashMap;
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
        int offset = (curPage - 1) * perPage;
        PostService service = new PostService();
        
        HashMap<String, Object> map = new HashMap<>();
        map.put("postBoard", postBoard);
        map.put("offset", offset);
        map.put("perPage", perPage);
        map.put("curPage", curPage);
        
        searchBoard(paramMap, map);
        
        System.out.println(map);
        
        PageDTO<PostPageDTO> pageDTO = service.getPostsByPage(map);

        List<PostPageDTO> list = pageDTO.getList();
        System.out.println(list);
        
        model.put("pDTO", pageDTO); // 페이지 정보를 모델에 추가
        model.put("postBoard", postBoard); // 게시판 이름을 모델에 추가

        return "board/boardView"; // 게시판 뷰 페이지의 이름을 반환
    }

	private void searchBoard(Map<String, String> paramMap, HashMap<String, Object> map) {
		String searchPosition = paramMap.get("selectSearchPositionText");
		String searchText = paramMap.get("inputSearchFreeText");
		if (searchPosition !=null && searchText != null) {
			switch (searchPosition) {
				case "titleText": {
					map.put("postTitle", searchText.trim());
					map.put("postText", searchText.trim());
					break;
				}
				case "postTitle": {
					map.put("postTitle", searchText.trim());
					break;
				}
				case "postText": {
					map.put("postText", searchText.trim());
					break;
				}
				case "userId": {
					map.put("userId", searchText.trim());
					break;
				}
			}
		}

	}
}

	
