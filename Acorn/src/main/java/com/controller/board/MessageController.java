package com.controller.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.controller.board.util.BoardController;
import com.dto.board.MessageDTO;
import com.service.MessageService;

public class MessageController implements BoardController  {

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) throws IOException {
		
        String senderId = paramMap.get("userId");

        
		MessageService mService = new MessageService();
    	List<MessageDTO> sList = mService.selectSendMessage(senderId);
		model.put("sendedMessage", sList);
		
		
		String receiverId=paramMap.get("receiverId");
		List<MessageDTO> rList = mService.selectReceiveMessage(senderId);
		model.put("receivedMessage", rList);
		
	
		
		String messageContent=paramMap.get("messageContent");
		if(receiverId==null || messageContent==null) {
			
			return "board/message";
			
		}

		// post
		
		HashMap<String, String> map = new HashMap<>(); 
		map.put("senderId", senderId);
		map.put("receiverId", receiverId);
		map.put("messageContent", messageContent);
	

		mService.insert(map);
		
	
		
		return "redirect:test";
		
		
		
	}

}
