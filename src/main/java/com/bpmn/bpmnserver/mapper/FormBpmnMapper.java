package com.bpmn.bpmnserver.mapper;


import com.bpmn.bpmnserver.common.FormBpmn;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FormBpmnMapper {


    @Select("select bpmn_id, form_id, bpmn_map, form_map from form_bpmn")
    List<FormBpmn> queryAllFormBpmn();

    @Select("select bpmn_id, form_id, bpmn_map, form_map from form_bpmn  where bpmn_id = #{bpmnId}")
    List<FormBpmn> queryFormBpmnBybpmnId(String bpmnId);

    @Select("select bpmn_id, form_id, bpmn_map, form_map from form_bpmn  where form_id = #{formId}")
    FormBpmn queryFormBpmnByformId(String formId);

    @Insert("insert into form_bpmn (bpmn_id, form_id, bpmn_map, form_map) values (#{bpmnId},#{formId},#{bpmnMap},#{formMap}) ")
    void insertOne(FormBpmn formBpmn);

    @Select("select count(form_id) from form_bpmn")
    Integer countAll();


    @Delete("delete from form_bpmn where form_id = #{formId}")
    void deleteFormBpmn(FormBpmn formBpmn);


    @Update({"<script>", "update form_bpmn f ",
            "  <set>",
            "    <if test='formId != null'>f.form_id=#{formId},</if>",
            "    <if test='bpmnId != null'>f.bpmn_id=#{bpmnId},</if>",
            "    <if test='formMap != null'>f.form_map=#{formMap},</if>",
            "    <if test='bpmnMap != null'>f.bpmn_map=#{bpmnMap},</if>",
            "  </set>",
            "where f.form_id = #{formId} ",
            "</script>"})
    int updateFormBpmnById(FormBpmn formBpmn);

}
