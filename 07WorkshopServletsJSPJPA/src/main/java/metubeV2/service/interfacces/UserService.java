package metubeV2.service.interfacces;

import metubeV2.domain.models.service.UserServiceModel;

import java.util.Optional;

public interface UserService {

    boolean registerUser(UserServiceModel userServiceModel);

    boolean loginUser(UserServiceModel userServiceModel);

    Optional<UserServiceModel> findUserByUsername(String username);
}
