package com.bpmn.bpmnserver.controller;

import com.bpmn.bpmnserver.common.Bpmn;
import com.bpmn.bpmnserver.common.FormBpmn;
import com.bpmn.bpmnserver.result.Result;
import com.bpmn.bpmnserver.service.FormBpmnService;
import com.bpmn.bpmnserver.utils.UtilsPublic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "表单流程管理")
@RequestMapping("/formBpmn")
@RestController
@CrossOrigin("*")
public class FormBpmnController {

    @Autowired
    public UtilsPublic myUtilsPublic;

    @Autowired
    private Result myResult;

    @Autowired
    public FormBpmnService formBpmnService;


    @ApiOperation("获取全部表单流程相关信息----queryAllFormBpmn")
    @GetMapping("/queryAllFormBpmn")
    public Result<List<FormBpmn>> queryAllFormBpmn() {
        return myResult.setOk(formBpmnService.queryAllFormBpmn());
    }

    @ApiOperation("根据流程id获取全部表单流程相关信息----queryAllFormBpmn")
    @GetMapping("/queryFormBpmnBybpmnId/{bpmnId}")
    public Result<List<FormBpmn>> queryFormBpmnBybpmnId(@PathVariable("bpmnId") String bpmnId) {
        return myResult.setOk(formBpmnService.queryFormBpmnBybpmnId(bpmnId));
    }

    @ApiOperation("根据表单id获取表单流程相关信息----queryFormBpmnByformId")
    @GetMapping("/queryFormBpmnByformId/{formId}")
    public Result<FormBpmn> queryFormBpmnByformId(String formId){
        return myResult.setOk(formBpmnService.queryFormBpmnByformId(formId));
    }

    @ApiOperation("根据ID获取流程信息并读取文件信息----queryFormBpmnMsgById")
    @GetMapping("/queryFormBpmnMsgById/{formId}")
    public Result<Map> queryBpmnMsgById(@PathVariable("formId") String formId) {
        Map m = new HashMap<String, String>();
        FormBpmn formBpmn = formBpmnService.queryFormBpmnByformId(formId);
        m.put("formId",formId);
        m.put("bpmnId",formBpmn.getBpmnId());
        m.put("bpmnMap", myUtilsPublic.readBpmnFile(formId, formBpmn.getBpmnMap()));
        m.put("formMap", myUtilsPublic.readBpmnFile(formId, formBpmn.getFormMap()));
        return myResult.setOk(m);
    }

    @ApiOperation("注解存在更新记录，否插入一条记录----saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    public Result<FormBpmn> saveOrUpdate(@RequestParam("bpmnFile") MultipartFile bpmnFile,
                                         @RequestParam("formFile") MultipartFile formFile,
                                         FormBpmn formbpmn)
            throws Exception {
        String formId = formbpmn.getFormId();
        String bpmnFileName = myUtilsPublic.generateUniqueFileName(bpmnFile.getOriginalFilename());
        String formFileName = myUtilsPublic.generateUniqueFileName(formFile.getOriginalFilename());
        formbpmn.setBpmnMap(bpmnFileName);
        formbpmn.setFormMap(formFileName);
        myUtilsPublic.createFormFile(bpmnFile, bpmnFileName, formId);
        myUtilsPublic.createFormFile(formFile, formFileName, formId);
        if (formBpmnService.queryFormBpmnByformId(formId) == null) {
            formBpmnService.insertOne(formbpmn);
        } else {
            formBpmnService.updateFormBpmnById(formbpmn);
        }
        return myResult.setOk(formbpmn);
    }

}
