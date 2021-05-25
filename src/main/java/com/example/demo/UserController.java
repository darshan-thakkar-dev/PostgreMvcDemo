package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView fillFormAndGotoDashboard(@ModelAttribute(value = "user") UserDTO user) {
        user.setUserType("Doctor");
        System.out.println("------------------");
        System.out.println(user);
        System.out.println("------------------");
        return new ModelAndView("dashboard");
    }

    @GetMapping("dashboard")
    public ModelAndView patientList(Model model) {
        model.addAttribute("patients", userService.getAllPatients());
        return new ModelAndView("dashboard");
    }

    @GetMapping("add-patient")
    public ModelAndView addPatient() {
        return new ModelAndView("patient_form");
    }

    @PostMapping("add-patient")
    public ModelAndView submitPatientForm() {
        return new ModelAndView("dashboard");
    }

}
