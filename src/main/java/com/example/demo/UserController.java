package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

	private final UserService userService;

	@GetMapping("/")
	public ModelAndView homePage() {
		return new ModelAndView("index");
	}

	@GetMapping("/register")
	public ModelAndView registrationPage() {
		return new ModelAndView("register");
	}

	@PostMapping("/register")
	public String registerAndGotoDashboard(@ModelAttribute(value = "user") UserDTO user, Model model, HttpSession session) {
		user.setUserType("Doctor");
		if (Objects.nonNull(userService.isUsernameAlreadyExists(user.getUsername()))) {
			model.addAttribute("isError", true);
			return "register";
		}
		UserDTO savedUser = userService.save(user);
		session.setAttribute("person", savedUser);
		model.addAttribute("person", savedUser);
		return "redirect:/dashboard";
	}

	@GetMapping("/login")
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}

	@PostMapping("/login")
	public String loginAndGotoDashboard(@ModelAttribute(value = "user") UserDTO user, Model model, HttpSession session) {
		user.setUserType("Doctor");
		UserDTO userDTO = userService.login(user.getUsername(), user.getPassword());

		if (Objects.isNull(userDTO)) {
			model.addAttribute("isError", true);
			return "login";
		}
		session.setAttribute("person", userDTO);
		model.addAttribute("person", userDTO);
		return "redirect:dashboard";
	}

	@GetMapping("dashboard")
	public ModelAndView patientList(@SessionAttribute(value = "person", required = false) UserDTO user, Model model) {
		if (Objects.isNull(user)) {
			return new ModelAndView("redirect:/");
		}
		model.addAttribute("patients", userService.getAllPatients());
		return new ModelAndView("dashboard");
	}

	@GetMapping("add-patient")
	public ModelAndView addPatient(@SessionAttribute(value = "person", required = false) UserDTO user, Model model) {
		if (Objects.isNull(user)) {
			return new ModelAndView("redirect:/");
		}
		model.addAttribute("person", new User());
		return new ModelAndView("patient_form");
	}

	@PostMapping("add-patient")
	public String submitPatientForm(@ModelAttribute(value = "person") UserDTO user) {
		user.setUserType("Patient");
		userService.save(user);
		return "redirect:dashboard";
	}

	@RequestMapping("add-patient/{id}")
	public ModelAndView editPatientForm(@SessionAttribute(value = "person", required = false) UserDTO user, @PathVariable(value = "id") Long id) {
		if (Objects.isNull(user)) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mv = new ModelAndView("patient_form");
		UserDTO editUser = userService.getUserByID(id);
		mv.addObject("person", editUser);
		return mv;
	}

	@RequestMapping("delete/{id}")
	public String deleteAndGotoDashboard(@SessionAttribute(value = "person", required = false) UserDTO user, @PathVariable(value = "id") Long id) {
		if (Objects.isNull(user)) {
			return "redirect:/";
		}
		userService.deleteUser(id);
		return "redirect:/dashboard";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session, SessionStatus sessionStatus) {
		session.removeAttribute("person");
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
