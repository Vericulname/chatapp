package com.sangng.chatroomservice.request;

import lombok.Data;

@Data
public class createChatMessageRequest {
    private Long chatRoomId;
    private Long senderId;
    private String content;
}
