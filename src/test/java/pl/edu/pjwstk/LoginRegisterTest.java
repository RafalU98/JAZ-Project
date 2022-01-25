package pl.edu.pjwstk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import pl.edu.pjwstk.User.Model.User;
import pl.edu.pjwstk.User.Repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class LoginRegisterTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("rafals@gmail.com");
        user.setPassword("rafafafa");
        user.setFirstName("Rafi");
        user.setLastName("Uber");
        User savedUser = repo.save(user);

        User existUserr = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUserr.getEmail());
    }

    @Test
    public void testFindByEmail() {
        String email = "rafals@gmail.com";
        User user = repo.findByEmail(email);
        assertThat(user.getEmail()).isEqualTo(email);
    }
}

