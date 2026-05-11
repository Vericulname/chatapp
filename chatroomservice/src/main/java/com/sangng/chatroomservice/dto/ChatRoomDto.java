package com.sangng.chatroomservice.dto;

import java.util.List;

import jakarta.persistence.ElementCollection;
import lombok.Data;

@Data
public class ChatRoomDto {
    private Long id;
    private List<Long> participantIds;
}
