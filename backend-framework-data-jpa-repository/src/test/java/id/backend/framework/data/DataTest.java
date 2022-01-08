package id.backend.framework.data;

import id.backend.framework.data.entity.User;
import id.backend.framework.data.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
public class DataTest {

    private TestEntityManager testEntityManager;

    private UserRepository userRepository;

    @Autowired
    public void setTestEntityManager(TestEntityManager testEntityManager) {
        this.testEntityManager = testEntityManager;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void initTest() {
        Assertions.assertNotNull(testEntityManager);
        Assertions.assertNotNull(userRepository);
    }

    @Test
    void saveSuccess() {
        User user = User.builder()
                .firstName("Cahyo Arif")
                .lastName("Andiyarto")
                .build();

        User result = userRepository.save(user);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(result.getFirstName(), user.getFirstName());
        Assertions.assertEquals(result.getLastName(), user.getLastName());
        Assertions.assertNotNull(result.getCreatedAt());
    }

    @Test
    void findByFirstNameSuccess() {
        User user = User.builder()
                .firstName("Cahyo Arif")
                .lastName("Andiyarto")
                .build();

        user = userRepository.save(user);

        Optional<User> userOptional = userRepository.findByFirstName("Cahyo Arif");

        Assertions.assertNotNull(userOptional);

        User user1 = userOptional.get();

        Assertions.assertEquals(user1, user);
    }

    @Test
    void findByFirstNameException() {
        User user = User.builder()
                .firstName("Cahyo Arif")
                .lastName("Andiyarto")
                .build();

        user = userRepository.save(user);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Optional<User> userOptional = userRepository.findByFirstName("Andiyarto");
            User user1 = userOptional.get();
        });
    }

    @SpringBootApplication
    public static class Application {}

}
