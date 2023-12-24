package com.bpmn.bpmnserver.mapper;


import com.bpmn.bpmnserver.common.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {


    @Select("select user_id, user_name, user_phone,user_password from user")
    List<User> queryAllUser();

    @Select("select user_id, user_name, user_phone,user_password from user where user_id = #{userId}")
    User queryUserById(String userId);

    @Insert("insert into user (user_id, user_name, user_phone,user_password) values (#{userId}, #{userName}, #{userPhone}, #{userPassword}) ")
    void insertOne(User user);

    @Select("select count(user_id) from user")
    Integer countAll();


    @Delete("delete from user where user_id = #{userId} ")
    void deleteUser(User user);


    @Update({"<script>", "update user u ",
            "  <set>",
            "    <if test='userId != null'>u.user_id=#{userId},</if>",
            "    <if test='userName != null'>u.user_name=#{userName},</if>",
            "    <if test='userPhone != null'>u.user_phone=#{userPhone},</if>",
            "    <if test='userPassword != null'>u.user_password=#{userPassword},</if>",
            "  </set>",
            "where u.user_id = #{userId} ",
            "</script>"})
    int updateUserById(User user);
}
