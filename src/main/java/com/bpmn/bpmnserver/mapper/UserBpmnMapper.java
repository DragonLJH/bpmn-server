package com.bpmn.bpmnserver.mapper;


import com.bpmn.bpmnserver.common.UserBpmn;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserBpmnMapper {

    @Select("select id, user_id, bpmn_id, mode  from user_bpmn")
    List<UserBpmn> queryAllUserBpmn();

    @Select("select id, user_id, bpmn_id, mode from user_bpmn where user_id = #{userId}")
    List<UserBpmn> queryUserBpmnByUserId(String userId);

    @Insert("insert into user_bpmn (id, user_id, bpmn_id, mode) values (#{id}, #{userId}, #{bpmnId}, #{mode}) ")
    void insertOne(UserBpmn userBpmn);

    @Select("select count(id) from user_bpmn")
    Integer countAll();


    @Delete("delete from user_bpmn where user_id = #{userId} ")
    void deleteUserBpmn(UserBpmn userBpmn);


    @Update({"<script>", "update user_bpmn u ",
            "  <set>",
            "    <if test='id != null'>u.id=#{id},</if>",
            "    <if test='userId != null'>u.user_id=#{userId},</if>",
            "    <if test='bpmnId != null'>u.bpmn_id=#{bpmnId},</if>",
            "    <if test='mode != null'>u.mode=#{mode},</if>",
            "  </set>",
            "where u.id = #{id} ",
            "</script>"})
    int updateUserBpmnById(UserBpmn userBpmn);
}
