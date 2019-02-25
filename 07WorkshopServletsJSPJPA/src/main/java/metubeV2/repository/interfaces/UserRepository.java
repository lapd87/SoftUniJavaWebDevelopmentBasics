package metubeV2.repository.interfaces;

import metubeV2.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User, String> {

    Optional findByName(String username);
}
