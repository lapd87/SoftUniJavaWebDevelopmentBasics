package metubeV2.service;

import metubeV2.domain.entities.User;
import metubeV2.domain.models.service.UserServiceModel;
import metubeV2.repository.interfaces.UserRepository;
import metubeV2.service.interfacces.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.RollbackException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper
                .map(userServiceModel, User.class);

        String shaPass = encryptPassword(userServiceModel.getId(), userServiceModel.getPassword());

        user.setPassword(shaPass);

        try {
            this.userRepository.save(user);
        } catch (RollbackException rbe) {
            return false;
        }

        return true;
    }

    @Override
    public boolean loginUser(UserServiceModel userServiceModel) {
        Optional userByName = this.userRepository
                .findByName(userServiceModel.getUsername());

        if (userByName.isEmpty()) {
            return false;
        }

        String shaPass = encryptPassword(userServiceModel.getId(), userServiceModel.getPassword());

        User user = (User) userByName.get();

        return user.getPassword()
                .equals(shaPass);
    }

    @Override
    public Optional<UserServiceModel> findUserByUsername(String username) {
        Optional userByName = this.userRepository.findByName(username);

        if (userByName.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(this.modelMapper
                .map(userByName.get(), UserServiceModel.class));
    }

    private String encryptPassword(String salt, String password) {

        String shaPass = DigestUtils.sha256Hex(password + salt);

        for (int i = 0; i < 3; i++) {
            shaPass = DigestUtils.sha256Hex(shaPass + salt + password);
        }
        return shaPass;
    }
}
