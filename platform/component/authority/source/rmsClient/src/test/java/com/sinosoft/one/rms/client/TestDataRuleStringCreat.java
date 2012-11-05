package com.sinosoft.one.rms.client;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sinosoft.one.rms.client.EnvContext;
import com.sinosoft.one.rms.clientService.User;


@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-test.xml" })
public class TestDataRuleStringCreat extends AbstractJUnit4SpringContextTests{

	@Autowired
	private TestService	testService;
	@Test
	public void test(){
//		User user= rmsClientService.login("admin", "00","RMS");
		User user=new User();
		user.setUserCode("admin");
		user.setLoginComCode("00");
		EnvContext.setLoginInfo(user);
		testService.testFindByHql();
	}
	
}
