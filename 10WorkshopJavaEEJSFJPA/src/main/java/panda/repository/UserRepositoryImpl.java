package panda.repository;

import panda.domain.entities.User;
import panda.repository.interfaces.UserRepository;
import panda.repository.util.EntityGetter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

import static panda.constants.Constants.PARAMETER_USERNAME;
import static panda.constants.Constants.SINGLE_RESULT;

public class UserRepositoryImpl extends GenericRepositoryImpl<User, String>
        implements UserRepository {

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional findByUsername(String username) {

        return EntityGetter.getEntityByParameter(username,
                PARAMETER_USERNAME,
                this.entityManager,
                User.class,
                SINGLE_RESULT);
    }
}
