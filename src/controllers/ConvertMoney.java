package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConvertMoney {
    @GetMapping("/convert")
    public String convert(@RequestParam(name = "dollar") String name, Model modle) {
        int VND = Integer.parseInt(name) * 23000;
        modle.addAttribute("VND", VND);
        return "result";
    }

    @GetMapping("/dollar")
    public String dollar(String name, Model modle){
        return "index";
    }
}
