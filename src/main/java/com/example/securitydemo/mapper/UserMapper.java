package com.example.securitydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.securitydemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}