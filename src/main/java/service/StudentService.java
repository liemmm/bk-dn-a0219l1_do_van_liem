package service;
import moudel.Student;
import java.util.List;

public interface StudentService {
    List<Student> findAll();

    void save(Student student) throws IllegalAccessException;

    Student findById(int id);

    void update(int id, Student student);

    void remove(int id);
}