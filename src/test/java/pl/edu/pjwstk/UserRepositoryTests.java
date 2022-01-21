package pl.edu.pjwstk;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.edu.pjwstk.User.Repository.UserRepository;
import pl.edu.pjwstk.User.Model.User;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired private UserRepository userRepository;

    @Test
    public void testAddNewUser() {
        User user =  new User();
        user.setEmail("rafal11.ubermanowicz@o2.pl");
        user.setPassword("rafal12332");
        user.setFirstName("Rafal");
        user.setLastName("Ubermanowicz");

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser)
                .isNotNull();
        Assertions.assertThat(savedUser
                .getId())
                .isGreaterThan(0);
    }


    @Test
    public void testListAll() {
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users)
                .hasSizeGreaterThan(0);

        for (User user: users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 21;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user= optionalUser.get();
        user.setPassword("OnlyMe");
        userRepository.save(user);

        User updatedUser = userRepository.findById(userId).get();
        Assertions.assertThat(updatedUser
                .getPassword())
                .isEqualTo("OnlyMe");
    }

    @Test
    public void testGet() {
        Integer userId = 22;
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();

        Assertions.assertThat(optionalUser)
                .isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 22;
        userRepository.deleteById(userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        Assertions.assertThat(optionalUser)
                .isNotPresent();
    }
}
