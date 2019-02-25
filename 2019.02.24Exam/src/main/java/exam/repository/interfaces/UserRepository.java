package exam.repository.interfaces;

import exam.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User, String> {

    Optional findByUsername(String username);
}
