package com.example.mall.dao;

import com.example.mall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User selectById(@Param("id")String id);

    User selectByName(@Param("username")String username);

    int insertUser(User user);

    int updatePassword(@Param("id")String id,@Param("password") String password);

}
