package com.sangng.chatroomservice.service.chatroom;

import java.util.List;

import com.sangng.chatroomservice.dto.ChatRoomDto;
import com.sangng.chatroomservice.dto.ChatMessageDto;
import com.sangng.chatroomservice.model.ChatRoom;

public interface IChatRoomService {

    public ChatRoom createChatRoom(List<Long> participantIds);

    public ChatRoom getChatRoom(Long chatRoomId);

    public List<ChatRoom> getAllChatRooms();

    public List<ChatRoom> getChatRoomsForUser(Long userId);

    public ChatRoomDto toDto(ChatRoom chatRoom);

    public List<ChatRoomDto> toDtoList(List<ChatRoom> chatRooms);

}
