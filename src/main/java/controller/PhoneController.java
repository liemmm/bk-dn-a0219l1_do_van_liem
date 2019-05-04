package controller;

import model.PhoneNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PhoneController {
    @GetMapping("/phone")
    public String showForm(Model model){
        model.addAttribute("phonenumber", new PhoneNumber());
        return "/phone";
    }
    @PostMapping("/phone")
    public String checkValidation (@Valid @ModelAttribute("phonenumber")PhoneNumber phoneNumber, BindingResult bindingResult, Model model){
        new PhoneNumber().validate(phoneNumber, bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/phone";
        }
        else {
            model.addAttribute("phoneNumber", phoneNumber.getNumber());
            return "/phoneResult";
        }
    }
}