package pl.edu.pjwstk.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.User.Model.Student;
import pl.edu.pjwstk.User.Repository.StudentRepository;
import pl.edu.pjwstk.User.Security.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired private StudentRepository studentRepository;

    public List<Student> listAll() {
        return (List<Student>) studentRepository
                .findAll();
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student get(Integer id) throws UserNotFoundException {
        Optional<Student> result = studentRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find a task with this id = " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = studentRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find anybody with this id" + id );
        }
        studentRepository.deleteById(id);
    }
}
