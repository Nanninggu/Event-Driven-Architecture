package com.example.EventDriven.Architecture.mapper;

import com.example.EventDriven.Architecture.dto.EventBox;
import com.example.EventDriven.Architecture.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    List<User> getUser(String id);

    @Insert("Insert into eventbox(id, \"eventType\", payload) " +
            "values(#{id}, #{eventType}, #{payload})")
    void insertOutbox(EventBox eventBox);
}