package groupe4.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import groupe4.user.model.User;
import groupe4.user.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/auth/{authenticateid}")
	public boolean isAuthenticate(@PathVariable Integer authenticateid) {
		return UserService.isAuthenticate(authenticateid);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/signup/{login}/{passwd}")
	public Integer signup(@PathVariable("login") String login, @PathVariable("passwd") String passwd) {
		Integer authenticateid = userService.signup(login, passwd);
		return authenticateid;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user/add/{authenticateid}")
	public void addUser(@RequestBody User user, Integer authenticateid) {
		if (UserService.isAuthenticate(authenticateid)) {
			userService.saveUser(user);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}/{authenticateid}")
	public User getUser(@PathVariable Integer id, @PathVariable Integer authenticateid) {
		if (UserService.isAuthenticate(authenticateid)) {
			User h = userService.getUser(id);
			return h;
		}
		return null;
	}
}