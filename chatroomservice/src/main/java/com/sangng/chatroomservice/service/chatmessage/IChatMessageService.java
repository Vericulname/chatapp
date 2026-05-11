package com.sangng.chatroomservice.service.chatmessage;

import java.util.List;

import com.sangng.chatroomservice.dto.ChatMessageDto;
import com.sangng.chatroomservice.model.ChatMessage;
import com.sangng.chatroomservice.request.createChatMessageRequest;

public interface IChatMessageService {

    public ChatMessage sendMessage(createChatMessageRequest request);

    public ChatMessage getMessage(Long messageId);

    public List<ChatMessage> getMessagesfromChatRoom(Long chatRoomId);

    public ChatMessage updateMessage(Long messageId, String newContent);

    public void deleteMessage(Long messageId);

    public ChatMessageDto toDto(ChatMessage message);

    public List<ChatMessageDto> toDtoList(List<ChatMessage> messages);

}
