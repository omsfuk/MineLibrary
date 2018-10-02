package cn.omsfuk.library.web.dao;

import cn.omsfuk.library.web.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDAO {

    User selectUserByUsername(@Param("username") String username);

    User selectUserById(@Param("id") String id);

    Integer insertUser(@Param("user") User user);

    Integer updateUser(@Param("user") User user);
}
