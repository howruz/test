package com.kosa.myapp.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.myapp.test.dao.ITestRepository;
import com.kosa.myapp.test.model.Test;

@Service
public class TestService implements ITestService {
	
	@Autowired
	ITestRepository testRepository;

	@Override
	public List<Test> getList() {
		return testRepository.getList();
	}

}
