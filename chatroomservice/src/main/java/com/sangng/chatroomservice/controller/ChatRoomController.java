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
import org.springframework.web.bind.annotation.RequestParam;

import com.sangng.chatroomservice.model.ChatRoomWrapper;



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
    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<ApiRespone> getChatRoomsForUser(@PathVariable("userId") Long userId) {
        try {
            List<ChatRoomDto> chatRooms = chatRoomService.getChatRoomsForUser(userId);
            return ResponseEntity.ok(new ApiRespone("Chat rooms for user retrieved successfully", chatRooms));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiRespone(e.getMessage(), null));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiRespone> createRoom(@RequestBody List<Long> participantIds) {
        try {
   
            ChatRoomWrapper chatRoomWrapper = chatRoomService.createChatRoom(participantIds);
            return ResponseEntity.ok(new ApiRespone("Chat room created successfully", chatRoomWrapper));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiRespone(e.getMessage(), null));
        }
       
    }
    
}