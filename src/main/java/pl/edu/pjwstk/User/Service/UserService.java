package pl.edu.pjwstk.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.User.Model.User;
import pl.edu.pjwstk.User.Repository.UserRepository;
import pl.edu.pjwstk.User.Security.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public List<User> listAll() {
        return (List<User>) userRepository
                .findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find a task with this id = " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find anybody with this id" + id );
        }
        userRepository.deleteById(id);
    }
}
