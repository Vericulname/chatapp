package com.sangng.chatroomservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sangng.chatroomservice.model.ChatMessage;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByChatRoomId(Long chatRoomId);

}
