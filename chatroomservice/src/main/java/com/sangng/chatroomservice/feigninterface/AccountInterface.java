package com.sangng.chatroomservice.feigninterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sangng.chatroomservice.respone.ApiRespone;

@FeignClient("accountservice")
public interface AccountInterface {
    @GetMapping("${api.prefix}/accounts/getByID/{id}")
   public ResponseEntity<ApiRespone> getAccountById(@PathVariable("id") Long id);
}
