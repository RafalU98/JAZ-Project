package pl.edu.pjwstk.User.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pjwstk.User.Model.User;

public interface
UserRepository extends CrudRepository<User, Integer>{
    public Long countById(Integer id);
}
