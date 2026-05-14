package com.sangng.chatroomservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class ChatRoomDto {
    private Long id;
    // private List<AccountDto> participants;
    private List<ChatMessageDto> messages;
}
