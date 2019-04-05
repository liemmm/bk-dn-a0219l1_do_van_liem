package controllers;

import moudel.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import service.StudentServiceImpl;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class StudentController {
    private StudentServiceImpl studentService = new StudentServiceImpl();

    @GetMapping("/list")
    public String convert(Model modle) {
        List<Student> students = studentService.findAll();
        modle.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/create")
    public String createStudentGet(Model modle, String message) {
        modle.addAttribute("student", new Student());
        modle.addAttribute("message", message);
        return "create";
    }

    @PostMapping("/create")
    public String createStudentPost(Model modle, @ModelAttribute("Student") Student student) {
        try {
            studentService.save(student);
            return convert(modle);
        } catch (Exception e) {
            return createStudentGet(modle, e.getMessage());
        }
    }


    @GetMapping("/delete")
    public String deletestudentGet(Model modle, @ModelAttribute("id") int id) {
        try {
            studentService.remove(id);
        } catch (Exception e) {

        }
        return convert(modle);
    }

    @GetMapping("/edit")
    public String edittudentGet(Model modle, @ModelAttribute("id") int id) {
        modle.addAttribute("student", studentService.findById(id));
        modle.addAttribute("id", id);
        return "edit";
    }

    @PostMapping("/edit")
    public String deletetudentGet(Model modle, @ModelAttribute("student") Student student, @ModelAttribute("id") int id) {
        studentService.update(id,student);
        return convert(modle);
    }

}
