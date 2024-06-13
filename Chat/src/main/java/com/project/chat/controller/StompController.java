package com.project.chat.controller;

import com.project.chat.domain.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달

    //Client가 SEND할 수 있는 경로
    //Config에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDto message){
        message.setContent(message.getMemberName() + "님이 입장하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDto message){
        template.convertAndSend("/sub/chat/room/" + message.getId(), message);
    }

}
