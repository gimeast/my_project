package com.project.chat.domain.dto;

import com.project.chat.domain.ChatRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ChatRoomDto {
    private Long id;
    private String name;

    public static ChatRoomDto chatRoomToDto(ChatRoom chatRoom) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setId(chatRoom.getId());
        chatRoomDto.setName(chatRoom.getName());
        return chatRoomDto;
    }

    public static List<ChatRoomDto> chatRoomToDtoList(List<ChatRoom> chatRooms) {
        List<ChatRoomDto> chatRoomDtoList = new ArrayList<>();
        for (ChatRoom chatRoom : chatRooms) {
            chatRoomDtoList.add(chatRoomToDto(chatRoom));
        }
        return chatRoomDtoList;
    }
}
