package com.bpmn.bpmnserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.bpmn.bpmnserver.common.FormBpmn;
import com.bpmn.bpmnserver.mapper.FormBpmnMapper;

@Service
@Transactional(rollbackFor = RuntimeException.class) // 异常回滚
public class FormBpmnService {
	@Autowired
	public FormBpmnMapper formBpmnMapper;

	public List<FormBpmn> queryAllFormBpmn() {
		return formBpmnMapper.queryAllFormBpmn();
	}

	public List<FormBpmn> queryFormBpmnBybpmnId(String bpmnId) {
		return formBpmnMapper.queryFormBpmnBybpmnId(bpmnId);
	}

	public FormBpmn queryFormBpmnByformId(String formId){
		return formBpmnMapper.queryFormBpmnByformId(formId);
	}
	public Boolean insertOne(FormBpmn formBpmn) {
		Integer f = formBpmnMapper.countAll();
		formBpmnMapper.insertOne(formBpmn);
		Integer s = formBpmnMapper.countAll();
		return f < s;
	}

	public Boolean deleteFormBpmn(FormBpmn formBpmn) {
		Integer f = formBpmnMapper.countAll();
		formBpmnMapper.deleteFormBpmn(formBpmn);
		Integer s = formBpmnMapper.countAll();
		return f > s;
	}

	public Boolean updateFormBpmnById(FormBpmn formBpmn) {
		return formBpmnMapper.updateFormBpmnById(formBpmn) == 1;
	}
	
	

}
