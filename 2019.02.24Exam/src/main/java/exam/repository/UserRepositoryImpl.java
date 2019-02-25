package exam.repository;

import exam.domain.entities.User;
import exam.repository.interfaces.UserRepository;
import exam.repository.util.EntityGetter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

import static exam.constants.Constants.PARAMETER_USERNAME;
import static exam.constants.Constants.SINGLE_RESULT;

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
