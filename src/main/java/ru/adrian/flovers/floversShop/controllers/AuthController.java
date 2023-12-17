package ru.adrian.flovers.floversShop.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.adrian.flovers.floversShop.Services.RegistrationServices;
import ru.adrian.flovers.floversShop.model.Person;
import ru.adrian.flovers.floversShop.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegistrationServices registrationServices;
@Autowired
    public AuthController(PersonValidator validator, RegistrationServices registrationServices) {
        this.personValidator = validator;
        this.registrationServices=registrationServices;
    }

    @GetMapping("/login")
    public String login(){

        return "auth/login";
    }
@GetMapping("/reg")
    public String registrationPage(@ModelAttribute("person")Person person){
        return "auth/reg";
}
@PostMapping(value= "/reg")
@Transactional
    public  String registrationPerform(@ModelAttribute("person")@Valid Person person, BindingResult bindingResult, Model model){
    System.out.println("запрошена регистрация");

    personValidator.validate(person, bindingResult);
if(bindingResult.hasErrors()){
    model.addAttribute("errors", bindingResult.getAllErrors());

    return "auth/reg";
}
registrationServices.register(person);
return "redirect:/auth/login";
}

}

