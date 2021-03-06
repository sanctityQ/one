package com.sinosoft.one.mvc.testcases.controllers.modulepath1;

import java.io.IOException;

import javax.servlet.ServletException;

import com.sinosoft.one.mvc.mock.controllers.modulepath1.IndexController;
import com.sinosoft.one.mvc.mock.controllers.modulepath1.submodulepath1.DefaultController;
import com.sinosoft.one.mvc.mock.controllers.modulepath1.submodulepath2.HomeController;
import com.sinosoft.one.mvc.mock.controllers.modulepath1.submodulepath2.sub3.WelcomeController;
import com.sinosoft.one.mvc.testcases.AbstractControllerTest;

public class ModulePathTest extends AbstractControllerTest {

	// module.path=${parent.module.path}/mp1
	public void testModulepath1() throws ServletException, IOException {
		assertEquals(IndexController.class, invoke("/mp1"));
	}

	// module.path=${parent.module.path}/smp1
	public void testModulepath1Submodulepath1() throws ServletException,
			IOException {
		assertEquals(DefaultController.class, invoke("/mp1/smp1"));
	}

	// module.path=smp2
	public void testModulepath1Submodulepath2() throws ServletException,
			IOException {
		assertEquals(HomeController.class, invoke("/mp1/smp2"));
	}

	// module.path=/sub3
	public void testModulepath1Submodulepath2Sub3() throws ServletException,
			IOException {
		assertEquals(WelcomeController.class, invoke("/sub3"));
	}

}
