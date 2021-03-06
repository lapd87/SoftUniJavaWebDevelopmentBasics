package exam.service.interfaces;

import exam.domain.models.service.UserServiceModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean userRegister(UserServiceModel userServiceModel);

    Optional<UserServiceModel> userLogin(UserServiceModel userServiceModel);

    Optional findUserByUsername(String username);

    List<UserServiceModel> findAllUsers();
}
