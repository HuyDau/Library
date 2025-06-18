package com.example.BE_Library.user.repository.mybatis;

import org.apache.ibatis.annotations.*;
import com.example.BE_Library.common.repository.mybatis.BaseMapper;
import com.example.BE_Library.user.entity.User;
import com.example.BE_Library.user.repository.query.SearchUserQuery;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper {

    @Select("""
        <script>
        SELECT user.id, user.email, user.full_name, user.phone_number, user.role
        FROM user ORDER BY user.full_name DESC
       </script>
    """)
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "fullName", column = "full_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "role", column = "role")
    })
    List<User> findUsers(@Param("query") SearchUserQuery query, @Param("offset") Integer offset, @Param("limit") Integer limit);


    @Select("<script>SELECT COUNT(*) FROM user</script>")
    long countUser(@Param("query") SearchUserQuery query);
}
