package metubeV2.repository;

import metubeV2.domain.entities.Tube;
import metubeV2.repository.interfaces.TubeRepository;
import metubeV2.repository.util.EntityGetter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

import static metubeV2.constants.Constants.*;

public class TubeRepositoryImpl implements TubeRepository {

    private final EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tube save(Tube entity) {
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
                Tube.class,
                MULTIPLE_RESULT);
    }

    @Override
    public Optional findById(String id) {
        return EntityGetter.getEntityByParameter(id,
                PARAMETER_ID,
                this.entityManager,
                Tube.class,
                SINGLE_RESULT);
    }

    @Override
    public Tube update(Tube tube) {
        this.entityManager.getTransaction().begin();
        Tube merged = this.entityManager.merge(tube);
        this.entityManager.getTransaction().commit();

        return merged;
    }
}
