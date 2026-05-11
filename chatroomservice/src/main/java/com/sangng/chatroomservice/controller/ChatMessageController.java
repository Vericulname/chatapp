package com.sangng.chatroomservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sangng.chatroomservice.dto.ChatMessageDto;
import com.sangng.chatroomservice.model.ChatMessage;
import com.sangng.chatroomservice.request.createChatMessageRequest;
import com.sangng.chatroomservice.respone.ApiRespone;
import com.sangng.chatroomservice.service.chatmessage.IChatMessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("${api.prefix}/messages")
@RequiredArgsConstructor
public class ChatMessageController {
    private final IChatMessageService chatMessageService;

    @PostMapping("/send")
    public ResponseEntity<ApiRespone> sendMessage(@RequestBody createChatMessageRequest request) {
        try {
            ChatMessage message = chatMessageService.sendMessage(request);
            ChatMessageDto messageDto = chatMessageService.toDto(message);
            return ResponseEntity.ok(new ApiRespone("Message sent successfully", messageDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiRespone(e.getMessage(), null));
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiRespone> getMessage(@PathVariable("id") Long id) {
        try {
            ChatMessage message = chatMessageService.getMessage(id);
            ChatMessageDto messageDto = chatMessageService.toDto(message);
            return ResponseEntity.ok(new ApiRespone("Message retrieved successfully", messageDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiRespone(e.getMessage(), null));
        }
    }

    @GetMapping("/getByChatRoomId/{chatRoomId}")
    public ResponseEntity<ApiRespone> getMessagesByChatRoomId(@PathVariable("chatRoomId") Long chatRoomId) {
        try {
            var messages = chatMessageService.getMessagesfromChatRoom(chatRoomId);
            var messageDtos = chatMessageService.toDtoList(messages);
            return ResponseEntity.ok(new ApiRespone("Messages retrieved successfully", messageDtos));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiRespone(e.getMessage(), null));
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiRespone> updateMessage(@PathVariable("id") Long id, @RequestBody String newContent) {
        try {
            ChatMessage updatedMessage = chatMessageService.updateMessage(id, newContent);
            ChatMessageDto messageDto = chatMessageService.toDto(updatedMessage);
            return ResponseEntity.ok(new ApiRespone("Message updated successfully", messageDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiRespone(e.getMessage(), null));
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiRespone> deleteMessage(@PathVariable("id") Long id) {
        try {
            chatMessageService.deleteMessage(id);
            return ResponseEntity.ok(new ApiRespone("Message deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiRespone(e.getMessage(), null));
        }
    }
}
