package service;

import moudel.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    private Map<Integer, Student> students;

    {
        students = new HashMap<>();
        students.put(1, new Student(1, "liemmm", 16, "Male", "Cẩm lệ", "Bk1090"));
        students.put(2, new Student(2, "yeu", 26, "Female", "code-gym", "tt123"));
        students.put(3, new Student(3, "ngan", 18, "Female", "ode-gym", "gg322"));
        students.put(4, new Student(4, "ghet", 24, "Male", "ko bit", "CNTT2016"));
        students.put(5, new Student(5, "tuan", 19, "Male", "gầm câu", "Bk1090"));
        students.put(6, new Student(6, "an", 16, "Male", "Cẩm lệ", "FPT 12"));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public void save(Student student) throws IllegalAccessException{
        if (!students.containsKey(student.getId())) {
            students.put(student.getId(), student);
        } else {
            throw new IllegalAccessException("ID is already in use");
        }
    }


    @Override
    public Student findById(int id) {
        return students.get(id);
    }

    @Override
    public void update(int id, Student customer) {
        students.put(id, customer);
    }

    @Override
    public void remove(int id) {
        students.remove(id);
    }
}
