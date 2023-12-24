package com.bpmn.bpmnserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpmn.bpmnserver.common.Bpmn;
import com.bpmn.bpmnserver.mapper.BpmnMapper;

@Service
@Transactional(rollbackFor = RuntimeException.class) // 异常回滚
public class BpmnService {
	@Autowired
	public BpmnMapper bpmnMapper;

	public List<Bpmn> queryAllBpmn() {
		return bpmnMapper.queryAllBpmn();
	}
	
	public Bpmn queryBpmnById(String bpmnId) {
		return bpmnMapper.queryBpmnById(bpmnId);
	}
	
	public Boolean insertOne(Bpmn bpmn) {
		Integer f = bpmnMapper.countAll();
		bpmnMapper.insertOne(bpmn);
		Integer s = bpmnMapper.countAll();
		return f < s;
	}
	
	public Boolean deleteBpmn(Bpmn bpmn) {
		Integer f = bpmnMapper.countAll();
		bpmnMapper.deleteBpmn(bpmn);
		Integer s = bpmnMapper.countAll();
		return f > s;
	}
	
	public Boolean updateBpmnById(Bpmn bpmn) {
		return bpmnMapper.updateBpmnById(bpmn) == 1;
	}
	
}
