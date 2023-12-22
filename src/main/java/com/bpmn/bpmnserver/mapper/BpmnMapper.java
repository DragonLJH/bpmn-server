package com.bpmn.bpmnserver.mapper;

import com.bpmn.bpmnserver.common.Bpmn;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BpmnMapper {

    @Select("select bpmn_id, xml_file, map_file from bpmn")
    List<Bpmn> queryAllBpmn();

    @Select("select bpmn_id, xml_file, map_file from bpmn  where bpmn_id = #{bpmnId}")
    Bpmn queryBpmnById(String bpmnId);

    @Insert("insert into bpmn (bpmn_id, xml_file, map_file) values (#{bpmnId}, #{xmlFile}, #{mapFile}) ")
    void insertOne(Bpmn bpmn);

    @Select("select count(bpmn_id) from bpmn")
    Integer countAll();


    @Delete("delete from bpmn where bpmn_id = #{bpmnId} ")
    void deleteBpmn(Bpmn bpmn);


    @Update({"<script>", "update bpmn b ",
            "  <set>",
            "    <if test='bpmnId != null'>b.bpmn_id=#{bpmnId},</if>",
            "    <if test='xmlFile != null'>b.xml_file=#{xmlFile},</if>",
            "    <if test='mapFile != null'>b.map_file=#{mapFile},</if>",
            "  </set>",
            "where b.bpmn_id = #{bpmnId} ",
            "</script>"})
    int updateBpmnById(Bpmn bpmn);
}
