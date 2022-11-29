package com.example.mall.dao;

import com.example.mall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User selectUserById(@Param("id")Integer id);

    User selectUserByName(@Param("username")String username);

    Integer selectIdByName(@Param("username")String username);

    Integer selectAccountById(@Param("id")Integer id);

    String selectTypeByName(@Param("username")String username);

    Integer insertUser(User user);

    Integer updatePassword(@Param("id")Integer id,@Param("password") String password);

}
