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

    //Client가 SEND할 수 있는 경로
    //Config에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDto message){
        message.setContent(message.getMemberName() + "님이 입장하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getId(), message);
        log.info("[채팅방 입장] message: {}", message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDto message){
        log.info("[채팅] message: {}", message);
        chatMessageService.addChatMessage(message);
        template.convertAndSend("/sub/chat/room/" + message.getId(), message);
    }

}
