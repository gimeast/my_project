package com.project.chat.service;

import com.project.chat.domain.ChatMessage;
import com.project.chat.domain.ChatRoom;
import com.project.chat.domain.Member;
import com.project.chat.domain.dto.ChatMessageDto;
import com.project.chat.repository.ChatMessageJpaRepository;
import com.project.chat.repository.ChatRoomJpaRepository;
import com.project.chat.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final ChatMessageJpaRepository chatMessageJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final ChatRoomJpaRepository chatRoomJpaRepository;

    public ChatMessageDto addChatMessage(ChatMessageDto chatMessageDto) {
        Member findMember = memberJpaRepository.findById(chatMessageDto.getMemberId()).orElseThrow();
        ChatRoom findChatRoom = chatRoomJpaRepository.findById(chatMessageDto.getChatRoomId()).orElseThrow();

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(chatMessageDto.getContent());
        chatMessage.setSendTime(LocalDateTime.now());
        chatMessage.setSender(findMember);
        chatMessage.setChatRoom(findChatRoom);

        return ChatMessageDto.chatMessageToDto(chatMessageJpaRepository.save(chatMessage));
    }

    public List<ChatMessageDto> getAllChatMessages(Long chatRoomId) {
        return ChatMessageDto.chatMessageToDtoList(chatMessageJpaRepository.findByChatRoomId(chatRoomId));
    }

}
