package com.bpmn.bpmnserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bpmn.bpmnserver.common.Bpmn;
import com.bpmn.bpmnserver.result.Result;
import com.bpmn.bpmnserver.service.BpmnService;
import com.bpmn.bpmnserver.utils.UtilsPublic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "流程管理")
@RequestMapping("/bpmn")
@RestController
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

	@ApiOperation("注解存在更新记录，否插入一条记录----saveOrUpdate")
	@PostMapping("/saveOrUpdate")
	public Result<Bpmn> saveOrUpdate(@RequestParam("file") MultipartFile file, @RequestParam("bpmn") Bpmn bpmn)
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
		}else {
			bpmnService.updateBpmnById(bpmn);
		}
		return myResult.setOk(bpmn);
	}

}
