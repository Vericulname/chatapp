package com.sangng.chatroomservice.model;

import java.util.List;

import com.sangng.chatroomservice.dto.AccountDto;

import lombok.Data;

@Data
public class ChatRoomWrapper {
    private Long id;
    private List<AccountDto> participants;
}
