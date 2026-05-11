package com.sangng.chatroomservice.service.chatroom;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sangng.chatroomservice.dto.ChatRoomDto;
import com.sangng.chatroomservice.dto.ChatMessageDto;
import com.sangng.chatroomservice.model.ChatRoom;
import com.sangng.chatroomservice.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService implements IChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ModelMapper modelMapper;

    @Override
    public ChatRoom getChatRoom(Long chatRoomId) {
        return Optional.ofNullable(chatRoomRepository.findById(chatRoomId)).get()
                .orElseThrow(() -> new RuntimeException("Chat room not found with id: " + chatRoomId));
    }

    @Override
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    @Override
    public List<ChatRoom> getChatRoomsForUser(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChatRoomsForUser'");
    }

    @Override
    public ChatRoom createChatRoom(List<Long> participantIds) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setParticipantIds(participantIds);
        return chatRoomRepository.save(chatRoom);
    }


    @Override
    public ChatRoomDto toDto(ChatRoom chatRoom) {
        return modelMapper.map(chatRoom, ChatRoomDto.class);
    }

    @Override
    public List<ChatRoomDto> toDtoList(List<ChatRoom> chatRooms) {
        return chatRooms.stream()
                .map(this::toDto)
                .toList();
    }

}
