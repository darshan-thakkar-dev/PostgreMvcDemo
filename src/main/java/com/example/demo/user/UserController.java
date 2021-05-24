package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

	private UserService userService;

	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		User user = new User();
		user.setUsername("darshan");
		user.setPassword("darshan");
		user.setUserType("Patient");
		UserDTO savedUser = userService.save(user);
		System.out.println(savedUser);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
}
