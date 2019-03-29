package controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.dictionary;

import java.util.HashMap;
import java.util.Map;


@Controller
public class dictionaryController {
    @Autowired
    private service.dictionary dictionary;

    @GetMapping("/dictionary")
    public String dictionary(Model model,String name){
        model.addAttribute("word","");
        return "index";
    }

    @PostMapping ("/dictionary")
    public String transplate(Model model,@RequestParam(name = "englishWord") String name){
        HashMap<String,String> engToVN = dictionary.getToVN();
        System.out.println(name);
        model.addAttribute("word",engToVN.get(name));
        return "index";
    }
}
