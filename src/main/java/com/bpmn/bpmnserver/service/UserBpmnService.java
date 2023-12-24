package com.bpmn.bpmnserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpmn.bpmnserver.common.UserBpmn;
import com.bpmn.bpmnserver.mapper.UserBpmnMapper;

@Service
@Transactional(rollbackFor = RuntimeException.class) // 异常回滚
public class UserBpmnService {

	@Autowired
	public UserBpmnMapper userBpmnMapper;

	public List<UserBpmn> queryAllUserBpmn() {
		return userBpmnMapper.queryAllUserBpmn();
	}

	public List<UserBpmn> queryUserBpmnByUserId(String userId) {
		return userBpmnMapper.queryUserBpmnByUserId(userId);
	}

	public Boolean insertOne(UserBpmn userBpmn) {
		Integer f = userBpmnMapper.countAll();
		userBpmnMapper.insertOne(userBpmn);
		Integer s = userBpmnMapper.countAll();
		return f < s;
	}

	public Boolean deleteUserBpmn(UserBpmn userBpmn) {
		Integer f = userBpmnMapper.countAll();
		userBpmnMapper.deleteUserBpmn(userBpmn);
		Integer s = userBpmnMapper.countAll();
		return f > s;
	}

	public Boolean updateUserBpmnById(UserBpmn userBpmn) {
		return userBpmnMapper.updateUserBpmnById(userBpmn) == 1;
	}
}
