package com.example.mall.dao;

import com.example.mall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User selectUserById(@Param("id")String id);

    User selectUserByName(@Param("username")String username);

    int selectIdByName(@Param("username")String username);

    String selectTypeByName(@Param("username")String username);

    int insertUser(User user);

    int updatePassword(@Param("id")String id,@Param("password") String password);

}
