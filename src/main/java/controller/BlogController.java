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

import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    AuthorService authorService;

    @ModelAttribute("author")
    Iterable<Author> authors(Pageable pageable) {
        return authorService.findAll(pageable);
    }

    @GetMapping("/blog")
    public ModelAndView list(@ModelAttribute("s") Optional<String> s, Pageable pageable) {
        ModelAndView mad = new ModelAndView("/blog/index");
        Iterable<Blog> blogs;
        if (s.isPresent()) {
            mad.addObject("blogs", blogService.findAllByTitleContaining(s.get(), pageable));
        } else {
            mad.addObject("blogs", blogService.findAll(pageable));
        }
        return mad;
    }

    @PostMapping("/create-blog")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog,
                                 @ModelAttribute("s") Optional<String> s, Pageable pageable) {
        blogService.save(blog);
        return list(s, pageable);
    }

    @GetMapping("/create-blog")
    public ModelAndView createBlog() {
        ModelAndView mad = new ModelAndView("/blog/create");
        mad.addObject("blog", new Blog());
        return mad;
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView editBlog(@ModelAttribute("id") Long id
    ) {
        ModelAndView mad = new ModelAndView("/blog/edit");
        mad.addObject("blog", blogService.findById(id));
        return mad;
    }

    @GetMapping("/delete-blog/{id}")
    public ModelAndView deleteBlog(@ModelAttribute("id") Long id,
                                   @ModelAttribute("s") Optional<String> s, Pageable pageable
    ) {
        blogService.remove(id);
        return list(s, pageable);
    }

    @PostMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog
            , @ModelAttribute("s") Optional<String> s, Pageable pageable) {
        blogService.save(blog);
        return list(s, pageable);
    }
}
