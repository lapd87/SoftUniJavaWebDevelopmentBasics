package metubeV2.repository;

import metubeV2.domain.entities.User;
import metubeV2.repository.interfaces.UserRepository;
import metubeV2.repository.util.EntityGetter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

import static metubeV2.constants.Constants.*;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Optional findAll() {
        return EntityGetter.getEntityByParameter(null,
                ALL_RESULT,
                this.entityManager,
                User.class,
                MULTIPLE_RESULT);
    }

    @Override
    public Optional findById(String id) {
        return EntityGetter.getEntityByParameter(id,
                PARAMETER_ID,
                this.entityManager,
                User.class,
                SINGLE_RESULT);
    }

    @Override
    public Optional findByName(String username) {
        return EntityGetter.getEntityByParameter(username,
                PARAMETER_USER,
                this.entityManager,
                User.class,
                SINGLE_RESULT);
    }
}
