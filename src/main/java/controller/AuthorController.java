package controller;

import model.Author;
import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.AuthorService;
import service.BlogService;

@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/author")
    public ModelAndView listAuthor(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/author/list");
        Iterable<Author> authors = authorService.findAll(pageable);
        modelAndView.addObject("author", authors);
        return modelAndView;
    }

    @GetMapping("/delete-author/{id}")
    public ModelAndView deleteAuthor(@ModelAttribute("id") Long id,Pageable pageable) {
        authorService.remove(id);
        return listAuthor(pageable);
    }


    @GetMapping("/edit-author/{id}")
    public ModelAndView getUpdateAuthor(@ModelAttribute("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/author/edit");
        modelAndView.addObject("author", authorService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit-author")
    public ModelAndView postupdateAuthor(@ModelAttribute("author") Author author,Pageable pageable) {
        authorService.save(author);
        return listAuthor(pageable);
    }

    @GetMapping("/create-author")
    public ModelAndView getCreateAuthor() {
        ModelAndView mad = new ModelAndView("/author/create");
        mad.addObject("author", new Author());
        return mad;
    }

    @PostMapping("/create-author")
    public ModelAndView postCreateAuthor(@ModelAttribute("author") Author author, Pageable pageable) {
        authorService.save(author);
        return listAuthor(pageable);
    }
}
