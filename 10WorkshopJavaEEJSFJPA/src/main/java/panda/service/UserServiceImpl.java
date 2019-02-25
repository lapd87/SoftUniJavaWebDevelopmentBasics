package panda.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import panda.domain.entities.Role;
import panda.domain.entities.User;
import panda.domain.models.service.UserServiceModel;
import panda.repository.interfaces.UserRepository;
import panda.service.interfaces.UserService;

import javax.inject.Inject;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static panda.constants.Constants.WARNINGS_UNCHECKED;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean userRegister(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);

        String shaPass = encryptPassword(userServiceModel.getUsername(),
                userServiceModel.getPassword());

        user.setPassword(shaPass);

        this.setUserRole(user);

        try {
            this.userRepository.save(user);
            return true;

        } catch (RollbackException rbe) {
            return false;
        }
    }

    @Override
    public Optional<UserServiceModel> userLogin(UserServiceModel userServiceModel) {
        Optional userByUsername = this.userRepository.findByUsername(userServiceModel.getUsername());

        if (userByUsername.isEmpty()) {
            return Optional.empty();
        }

        String shaPass = encryptPassword(userServiceModel.getUsername(),
                userServiceModel.getPassword());

        UserServiceModel user = this.modelMapper
                .map(userByUsername.get(), UserServiceModel.class);

        if (!user.getPassword().equals(shaPass)) {
            return Optional.empty();
        }

        return Optional.of(user);
    }

    @Override
    public Optional findUserByUsername(String username) {
        Optional optionalUser = this.userRepository
                .findByUsername(username);

        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }

        UserServiceModel userServiceModel = this.modelMapper
                .map(optionalUser.get(), UserServiceModel.class);

        return Optional.ofNullable(userServiceModel);
    }

    @SuppressWarnings(WARNINGS_UNCHECKED)
    @Override
    public List<UserServiceModel> findAllUsers() {
        Optional optionalUsers = this.userRepository.findAll();

        if (optionalUsers.isEmpty()) {
            return new ArrayList<>();
        }

        List<User> allUsers = (List<User>) optionalUsers.get();

        return allUsers.stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    private void setUserRole(User user) {
        user.setRole(this.userRepository.size() == 0 ? Role.ADMIN : Role.USER);
    }

    private String encryptPassword(String salt, String password) {

        String shaPass = DigestUtils.sha256Hex(password + salt);

        for (int i = 0; i < 3; i++) {
            shaPass = DigestUtils.sha256Hex(shaPass + salt + password);
        }
        return shaPass;
    }
}
