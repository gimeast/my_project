package com.project.chat.service;

import com.project.chat.domain.ChatRoom;
import com.project.chat.domain.dto.ChatRoomDto;
import com.project.chat.repository.ChatRoomJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomJpaRepository chatRoomJpaRepository;

    public List<ChatRoomDto> getChatRooms() {
        List<ChatRoom> chatRooms = chatRoomJpaRepository.findAll();
        return ChatRoomDto.chatRoomToDtoList(chatRooms);
    }

    public ChatRoomDto save(ChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(chatRoomDto.getName());
        return ChatRoomDto.chatRoomToDto(chatRoomJpaRepository.save(chatRoom));
    }

    public ChatRoomDto findById(Long id) {
        return ChatRoomDto.chatRoomToDto(chatRoomJpaRepository.findById(id).orElseThrow());
    }
}
