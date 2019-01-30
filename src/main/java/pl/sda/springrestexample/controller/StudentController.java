package pl.sda.springrestexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.springrestexample.dto.Student;
import pl.sda.springrestexample.repository.StudentRepository;

import java.io.IOException;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository repo;

    @GetMapping("/students")
    public List<Student> getAllStudents() throws IOException {
        return repo.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable("id") Long id) throws IOException {
        return repo.findById(id);
    }

    @PostMapping("/students")
    public void createStudent(@RequestBody Student student) throws IOException {
        repo.save(student);
    }

    @PutMapping ("/students/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable("id") Long id) throws IOException {
        repo.update(student, id);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") Long id) throws IOException {
        repo.delete(id);
    }
}
