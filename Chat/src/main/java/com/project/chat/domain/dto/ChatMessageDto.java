package com.project.chat.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.chat.domain.ChatMessage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChatMessageDto {
    private Long id;
    private String content;
    private LocalDateTime sendTime;
    private Long memberId;
    private String memberName;
    private Long chatRoomId;

    public ChatMessageDto() {}

    public ChatMessageDto(String content, Long memberId, String memberName, Long chatRoomId) {
        this.content = content;
        this.memberId = memberId;
        this.memberName = memberName;
        this.chatRoomId = chatRoomId;
    }

    public static ChatMessageDto chatMessageToDto(ChatMessage chatMessage) {
        ChatMessageDto chatMessageDto = new ChatMessageDto();
        chatMessageDto.setId(chatMessage.getId());
        chatMessageDto.setContent(chatMessage.getContent());
        chatMessageDto.setSendTime(chatMessage.getSendTime());
        chatMessageDto.setMemberId(chatMessage.getSender().getId());
        chatMessageDto.setMemberName(chatMessage.getSender().getName());
        chatMessageDto.setChatRoomId(chatMessage.getChatRoom().getId());
        return chatMessageDto;
    }

    public static List<ChatMessageDto> chatMessageToDtoList(List<ChatMessage> chatMessage) {
        List<ChatMessageDto> chatMessageDtoList = new ArrayList<>();
        for (ChatMessage message : chatMessage) {
            chatMessageDtoList.add(chatMessageToDto(message));
        }
        return chatMessageDtoList;
    }
}
