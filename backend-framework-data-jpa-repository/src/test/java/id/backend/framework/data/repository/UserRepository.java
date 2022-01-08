package id.backend.framework.data.repository;

import id.backend.framework.data.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByFirstName(String firstName);

}
