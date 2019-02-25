package exam.repository;

import exam.domain.entities.Document;
import exam.repository.interfaces.DocumentRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DocumentRepositoryImpl extends GenericRepositoryImpl<Document, String>
        implements DocumentRepository {

    private final EntityManager entityManager;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

}
