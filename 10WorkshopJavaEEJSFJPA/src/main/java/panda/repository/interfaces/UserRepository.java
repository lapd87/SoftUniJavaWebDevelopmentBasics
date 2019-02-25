package panda.repository.interfaces;

import panda.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User, String> {

    Optional findByUsername(String username);
}
