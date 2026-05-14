package com.sangng.accountservice.feignInterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sangng.accountservice.respone.ApiRespone;

@FeignClient("chatroomservice")
public interface chatRoomInterface {
         @GetMapping("/getById/{id}")
         public ResponseEntity<ApiRespone> getChatRoomById(@PathVariable("id") Long id);
}
