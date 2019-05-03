package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    public Author(String name, int age, List<Blog> blogs) {
        this.name = name;
        this.age = age;
        this.blogs = blogs;
    }

    public Author() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;
    @OneToMany(targetEntity = Blog.class)
    private List<Blog> blogs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
