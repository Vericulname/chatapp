package com.sangng.chatroomservice.service.chatmessage;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sangng.chatroomservice.dto.ChatMessageDto;
import com.sangng.chatroomservice.model.ChatMessage;
import com.sangng.chatroomservice.model.ChatRoom;
import com.sangng.chatroomservice.repository.ChatRoomRepository;
import com.sangng.chatroomservice.repository.MessageRepository;
import com.sangng.chatroomservice.request.createChatMessageRequest;
import com.sangng.chatroomservice.service.chatroom.IChatRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageService implements IChatMessageService {

    private final MessageRepository messageRepository;
    private final IChatRoomService chatRoomService;
    private final ModelMapper modelMapper;

    @Override
    public ChatMessage sendMessage(createChatMessageRequest request) {
       
        
        return Optional.of(request).map(messages -> {
            ChatRoom chatRoom = chatRoomService.getChatRoom(request.getChatRoomId());
            
            ChatMessage message = new ChatMessage();
            message.setChatRoom(chatRoom);
            message.setSenderId(request.getSenderId());
            message.setContent(request.getContent());
            message.setTimestamp(java.time.LocalDateTime.now());
            return messageRepository.save(message);

        }).orElseThrow(() -> new RuntimeException("Chat room not found with id: " + request.getChatRoomId()));
    }

    @Override
    public ChatMessage getMessage(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + messageId));
    }

    @Override
    public List<ChatMessage> getMessagesfromChatRoom(Long chatRoomId) {
        return messageRepository.findByChatRoomId(chatRoomId);
    }

    @Override
    public ChatMessage updateMessage(Long messageId, String newContent) {
        ChatMessage message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + messageId));
        message.setContent(newContent);
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Long messageId) {
        messageRepository.findById(messageId).ifPresentOrElse(messageRepository::delete, () -> {
            throw new RuntimeException("Message not found with id: " + messageId);
        });
    }

    @Override
    public ChatMessageDto toDto(ChatMessage message) {
        return modelMapper.map(message, ChatMessageDto.class);
    }

    @Override
    public List<ChatMessageDto> toDtoList(List<ChatMessage> messages) {
        return messages.stream().map(this::toDto).toList();
    }

    

}
