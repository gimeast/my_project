package com.project.chat.controller;

import com.project.chat.domain.dto.ChatMessageDto;
import com.project.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final ChatMessageService chatMessageService;


    @MessageMapping("/message")
    public void messaging(ChatMessageDto message) throws Exception {
//        Thread.sleep(1000); // simulated delay
        String destination = "/topic/messages/" + message.getChatRoomId();
        chatMessageService.addChatMessage(message);
        template.convertAndSend(destination, message);
    }

}
