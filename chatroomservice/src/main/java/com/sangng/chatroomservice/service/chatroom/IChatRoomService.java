package com.sangng.chatroomservice.service.chatroom;

import java.util.List;

import com.sangng.chatroomservice.dto.ChatRoomDto;
import com.sangng.chatroomservice.dto.ChatMessageDto;
import com.sangng.chatroomservice.model.ChatRoom;
import com.sangng.chatroomservice.model.ChatRoomWrapper;

public interface IChatRoomService {

    public ChatRoomWrapper createChatRoom(List<Long> participantIds);

    public ChatRoom getChatRoom(Long chatRoomId);

    public List<ChatRoom> getAllChatRooms();

    public List<ChatRoomDto> getChatRoomsForUser(Long userId);

    public ChatRoomDto toDto(ChatRoom chatRoom);

    public List<ChatRoomDto> toDtoList(List<ChatRoom> chatRooms);

    public ChatRoomWrapper toWrapper(ChatRoom chatRoom);

    public List<ChatRoomWrapper> toWrapperList(List<ChatRoom> chatRooms);

}
