package com.sangng.chatroomservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sangng.chatroomservice.dto.ChatRoomDto;
import com.sangng.chatroomservice.model.ChatRoom;
import com.sangng.chatroomservice.respone.ApiRespone;
import com.sangng.chatroomservice.service.chatroom.IChatRoomService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("${api.prefix}/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {
    private final IChatRoomService chatRoomService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiRespone> getAllChatRooms() {
        List<ChatRoomDto> chatRooms = chatRoomService.toDtoList(chatRoomService.getAllChatRooms());
        return ResponseEntity.ok(new ApiRespone("Chat rooms retrieved successfully", chatRooms));
    }

     @GetMapping("/getById/{id}")
    public ResponseEntity<ApiRespone> getChatRoomById(@PathVariable("id") Long id) {
        try {
            ChatRoom chatRoom = chatRoomService.getChatRoom(id);
            ChatRoomDto chatRoomDto = chatRoomService.toDto(chatRoom);
            return ResponseEntity.ok(new ApiRespone("Chat room retrieved successfully", chatRoomDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiRespone(e.getMessage(), null));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiRespone> createRoom(@RequestBody List<Long> participantIds) {
        
        ChatRoom chatRoom = chatRoomService.createChatRoom(participantIds);
        ChatRoomDto chatRoomDto = chatRoomService.toDto(chatRoom);
        return ResponseEntity.ok(new ApiRespone("Chat room created successfully", chatRoomDto));
    }
    
}