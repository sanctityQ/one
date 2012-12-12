/**
 * 
 */
package com.sinosoft.one.ams.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.one.ams.model.Employe;
import com.sinosoft.one.ams.service.AccountManager;
import com.sinosoft.one.mvc.web.Invocation;
import com.sinosoft.one.mvc.web.annotation.Path;
import com.sinosoft.one.mvc.web.annotation.rest.Post;

@Path
public class LoginController {
	@Autowired
	private AccountManager accountManager;

	@Post
	public String login(Employe user, Invocation inv) {

		Employe checkUser = accountManager.findByUsername(user.getUserName());

		if (checkUser != null
				&& checkUser.getPassword().equals(user.getPassword())) {
			inv.getRequest().getSession().setAttribute("user", checkUser);
			return "index";
		} else {
			return "login";
		}

	}

}
