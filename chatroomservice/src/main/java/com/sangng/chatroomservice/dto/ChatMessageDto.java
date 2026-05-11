package com.sangng.chatroomservice.dto;

import lombok.Data;

@Data
public class ChatMessageDto {

    private Long id;

    private Long senderId;

    private String content;
    private java.time.LocalDateTime timestamp;
}
