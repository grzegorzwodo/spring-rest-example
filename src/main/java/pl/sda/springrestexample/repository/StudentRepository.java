package pl.sda.springrestexample.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import pl.sda.springrestexample.dto.Student;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentRepository {

    private String filePath;
    private Gson gson;

    public StudentRepository() throws IOException {
        this.filePath = "repo.json";
        File file = new File(filePath);
        if(!file.exists()) {
            file.createNewFile();

        }
        this.gson = new Gson();
    }

    public void save(Student student) throws IOException {
        List<Student> students = findAll();
        if(students == null) students = new ArrayList<>();
        students.add(student);

        FileWriter fileWriter = new FileWriter(filePath);
        gson.toJson(students, fileWriter);
        fileWriter.close();

    }

    public List<Student> findAll() throws IOException {
        FileReader fileReader = new FileReader(filePath);
        Type listType = new TypeToken<ArrayList<Student>>(){}.getType();
        List<Student> students = gson.fromJson(fileReader, listType);
        fileReader.close();
        return students;
    }

    public void delete(Long id) throws IOException {
        List<Student> students = findAll();
        List<Student> studentsNew = new ArrayList<>();
        for (Student s : students) {
            if(!s.getId().equals(id)) {
                studentsNew.add(s);
            }
        }
        FileWriter fileWriter = new FileWriter(filePath);
        gson.toJson(studentsNew, fileWriter);
        fileWriter.close();
    }

    public Student findById(Long id) throws IOException {
        List<Student> students = findAll();
        for (Student s : students) {
            if(s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public void update(Student student, Long id) throws IOException {
        List<Student> students = findAll();
        List<Student> studentsNew = new ArrayList<>();
        for (Student s : students) {
            if(s.getId().equals(id)) {
                studentsNew.add(student);
            } else {
                studentsNew.add(s);
            }
        }
        FileWriter fileWriter = new FileWriter(filePath);
        gson.toJson(studentsNew, fileWriter);
        fileWriter.close();
    }
}
