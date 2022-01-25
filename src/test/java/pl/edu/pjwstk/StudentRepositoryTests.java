package pl.edu.pjwstk;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.edu.pjwstk.User.Model.Student;
import pl.edu.pjwstk.User.Repository.StudentRepository;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class StudentRepositoryTests {

    @Autowired private StudentRepository studentRepository;

    @Test
    public void testAddNewUser() {
        Student student =  new Student();
        student.setEmail("rafal112.ubermanowicz@o2.pl");
        student.setFirstName("Rafal");
        student.setLastName("Ubermanowicz");

        Student savedStudent = studentRepository.save(student);

        Assertions.assertThat(savedStudent)
                .isNotNull();
        Assertions.assertThat(savedStudent
                .getId())
                .isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Student> users = studentRepository.findAll();
        Assertions.assertThat(users)
                .hasSizeGreaterThan(0);

        for (Student student : users){
            System.out.println(student);
        }
    }

    @Test
    public void testGet() {
        Integer userId = 6;
        Optional<Student> optionalStudent = studentRepository.findById(userId);
        Student student = optionalStudent.get();

        Assertions.assertThat(optionalStudent)
                .isPresent();
        System.out.println(optionalStudent.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 6;
        studentRepository.deleteById(userId);

        Optional<Student> optionalUser = studentRepository.findById(userId);
        Assertions.assertThat(optionalUser)
                .isNotPresent();
    }
}
