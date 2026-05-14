package com.sangng.chatroomservice.service.chatroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sangng.chatroomservice.dto.AccountDto;
import com.sangng.chatroomservice.dto.ChatRoomDto;
import com.sangng.chatroomservice.dto.ChatMessageDto;
import com.sangng.chatroomservice.feigninterface.AccountInterface;
import com.sangng.chatroomservice.model.ChatRoom;
import com.sangng.chatroomservice.model.ChatRoomWrapper;
import com.sangng.chatroomservice.repository.ChatRoomRepository;
import com.sangng.chatroomservice.respone.ApiRespone;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService implements IChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final AccountInterface accountInterface;
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
    public List<ChatRoomDto> getChatRoomsForUser(Long userId) {
        
        accountInterface.getAccountById(userId);
        List<ChatRoomDto> chatRoomDtos = Optional.ofNullable(chatRoomRepository.findByParticipantIdsContaining(userId)).get()
               
                .stream()
                .map(chatroom -> {
                    ChatRoomDto chatRoomDto = modelMapper.map(chatroom, ChatRoomDto.class);

                    // List<AccountDto> participants = chatroom.getParticipantIds().stream()
                    //         .map(participantId -> {
                      
                    //                 ResponseEntity<ApiRespone> response = accountInterface.getAccountById(participantId);
                    //                 return modelMapper.map(response.getBody().getData(), AccountDto.class);
                                
                    //         })
                    //         .toList();
                    List<ChatMessageDto> messages = chatroom.getMessages().stream()
                            .map(message -> modelMapper.map(message, ChatMessageDto.class))
                            .toList();
                            
                    // chatRoomDto.setParticipants(participants);
                    chatRoomDto.setMessages(messages);
                    return chatRoomDto;
                })
                .toList();

        return chatRoomDtos;
    }

    @Override
    public ChatRoomWrapper createChatRoom(List<Long> participantIds) {
        ChatRoom chatRoom = new ChatRoom();
        List<Long> validParticipantIds = new ArrayList<>();

        List<AccountDto> participants = participantIds.stream()
                .map(id -> {
                    ResponseEntity<ApiRespone> response = accountInterface.getAccountById(id);

                    validParticipantIds.add(id);
                    return modelMapper.map(response.getBody().getData(), AccountDto.class);

                })
                .toList();

        chatRoom.setParticipantIds(validParticipantIds);
        chatRoomRepository.save(chatRoom);

        ChatRoomWrapper chatRoomWrapper = modelMapper.map(chatRoom, ChatRoomWrapper.class);
        chatRoomWrapper.setParticipants(participants);
        return chatRoomWrapper;
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

    @Override
    public ChatRoomWrapper toWrapper(ChatRoom chatRoom) {
        return modelMapper.map(chatRoom, ChatRoomWrapper.class);
    }

    @Override
    public List<ChatRoomWrapper> toWrapperList(List<ChatRoom> chatRooms) {
        return chatRooms.stream()
                .map(this::toWrapper)
                .toList();
    }

}
