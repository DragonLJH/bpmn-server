package com.bpmn.bpmnserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpmn.bpmnserver.common.User;
import com.bpmn.bpmnserver.mapper.UserMapper;

@Service
@Transactional(rollbackFor = RuntimeException.class) // 异常回滚
public class UserService {

	@Autowired
	public UserMapper userMapper;

	public List<User> queryAllUser() {
		return userMapper.queryAllUser();
	}

	public User queryUserById(String userId) {
		return userMapper.queryUserById(userId);
	}

	public Boolean insertOne(User user) {
		Integer f = userMapper.countAll();
		userMapper.insertOne(user);
		Integer s = userMapper.countAll();
		return f < s;
	}


	public Boolean deleteUser(User user) {
		Integer f = userMapper.countAll();
		userMapper.deleteUser(user);
		Integer s = userMapper.countAll();
		return f > s;
	}

	public Boolean updateUserById(User user) {
		return userMapper.updateUserById(user) == 1;
	}
}
