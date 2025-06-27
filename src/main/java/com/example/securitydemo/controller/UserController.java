package com.example.securitydemo.controller;

import com.example.securitydemo.entity.User;
import com.example.securitydemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    @PreAuthorize("hasRole('ADMIN') and authentication.name.equals('admin')")
    @GetMapping("/list")
    public List<User> getList(){
        return userService.list();
    }

    @PreAuthorize("hasAuthority('USER_ADD')")
    @PostMapping("/add")
    // 没有注解的时候，都可以访问，配置了就必须是该类角色或具有某种权限才可以访问
//    @PreAuthorize("hasRole('USER')")
    public void add(@RequestBody User user){
        userService.saveUserDetails(user);
    }
}