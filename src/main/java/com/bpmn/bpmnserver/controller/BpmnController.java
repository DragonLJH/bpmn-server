package com.bpmn.bpmnserver.controller;

import com.bpmn.bpmnserver.common.Bpmn;
import com.bpmn.bpmnserver.result.Result;
import com.bpmn.bpmnserver.service.BpmnService;
import com.bpmn.bpmnserver.utils.UtilsPublic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "流程管理")
@RequestMapping("/bpmn")
@RestController
@CrossOrigin("*")
public class BpmnController<T> {

    @Autowired
    public UtilsPublic myUtilsPublic;

    @Autowired
    private BpmnService bpmnService;

    @Autowired
    private Result myResult;

    @ApiOperation("获取全部流程信息----queryAllBpmn")
    @GetMapping("/queryAllBpmn")
    public Result<List<Bpmn>> queryAllBpmn() {
        return myResult.setOk(bpmnService.queryAllBpmn());
    }

    @ApiOperation("根据ID获取流程信息----queryBpmnById")
    @GetMapping("/queryBpmnById/{bpmnId}")
    public Result<Bpmn> queryBpmnById(@PathVariable("bpmnId") String bpmnId) {
        return myResult.setOk(bpmnService.queryBpmnById(bpmnId));
    }

    @ApiOperation("根据ID获取流程信息并读取文件信息----queryBpmnMsgById")
    @GetMapping("/queryBpmnMsgById/{bpmnId}")
    public Result<Map> queryBpmnMsgById(@PathVariable("bpmnId") String bpmnId) {
        Map m = new HashMap<String, String>();
        Bpmn bpmn = bpmnService.queryBpmnById(bpmnId);
        m.put("bpmnId",bpmnId);
        m.put("bpmnMap", myUtilsPublic.readBpmnFile(bpmn.getBpmnId(), bpmn.getMapFile()));
        m.put("xml", myUtilsPublic.readBpmnFile(bpmn.getBpmnId(), bpmn.getXmlFile()));
        return myResult.setOk(m);
    }

    @ApiOperation("注解存在更新记录，否插入一条记录----saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    public Result<Bpmn> saveOrUpdate(@RequestParam("file") MultipartFile file, Bpmn bpmn)
            throws Exception {
        String bpmnId = bpmn.getBpmnId();
        String fileName = myUtilsPublic.generateUniqueFileName(file.getOriginalFilename());
        if (fileName.indexOf(".xml") != -1) {
            bpmn.setXmlFile(fileName);
        }
        if (fileName.indexOf(".json") != -1) {
            bpmn.setMapFile(fileName);
        }
        myUtilsPublic.createBpmnFile(file, fileName, bpmnId);
        if (bpmnService.queryBpmnById(bpmnId) == null) {
            bpmnService.insertOne(bpmn);
        } else {
            bpmnService.updateBpmnById(bpmn);
        }
        return myResult.setOk(bpmn);
    }

}
