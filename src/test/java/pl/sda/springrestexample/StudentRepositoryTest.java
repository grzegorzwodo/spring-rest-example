package pl.sda.springrestexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.springrestexample.dto.Student;
import pl.sda.springrestexample.repository.StudentRepository;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

	@Test
	public void testSaveStudentInNewFile() throws IOException {
		StudentRepository repo = new StudentRepository();
		List<Student> list = repo.findAll();

		System.out.println(list);

		repo.save(new Student(2L, "Test_2", "Test_2_2"));

		list = repo.findAll();

		System.out.println(list);

	}

}

