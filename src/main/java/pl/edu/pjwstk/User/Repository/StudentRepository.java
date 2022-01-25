package pl.edu.pjwstk.User.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.User.Model.Student;

public interface
StudentRepository extends CrudRepository<Student, Integer>{
    public Long countById(Integer id);
}
