package com.rongqi.manage.controller;

import com.rongqi.manage.pojo.User;
import com.rongqi.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> login(@RequestParam("account") String account){
        User user = userService.findByAccount(account);
        return ResponseEntity.ok(user);
    }

}
