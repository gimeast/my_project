package com.project.chat.controller;

import com.project.chat.domain.ChatRoom;
import com.project.chat.domain.Member;
import com.project.chat.domain.dto.ChatMessageDto;
import com.project.chat.domain.dto.ChatRoomDto;
import com.project.chat.domain.dto.MemberDto;
import com.project.chat.service.ChatMessageService;
import com.project.chat.service.ChatRoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    @GetMapping("/chat-rooms")
    public String chatRoomList(HttpSession session, Model model) {
        MemberDto member = (MemberDto) session.getAttribute("member");
        if(member == null) {
            return "redirect:/";
        }
        model.addAttribute("member", member);
        return "chat-room-list";
    }

    @ResponseBody
    @GetMapping("/chat-rooms/list")
    public List<ChatRoomDto> getChatRooms() {
        return chatRoomService.getChatRooms();
    }

    @ResponseBody
    @PostMapping("/chat-rooms")
    public ChatRoomDto addRoom(@RequestBody ChatRoomDto chatRoomDto) {
        log.info("[채팅방 추가]: {}", chatRoomDto);
        return chatRoomService.save(chatRoomDto);
    }

    @GetMapping("/chat-rooms/{id}")
    public String chatRoomDetail(HttpSession session, @PathVariable Long id, Model model) {
        MemberDto member = (MemberDto) session.getAttribute("member");

        // 기존 대화내용 있으면 화면단에 뿌린다.
        List<ChatMessageDto> messages = chatMessageService.getAllChatMessages(id);
        model.addAttribute("messages", messages);
        model.addAttribute("chatRoomId", id);
        model.addAttribute("member", member);

        return "chat-room";
    }

}
