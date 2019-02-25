package panda.repository;

import panda.domain.entities.Receipt;
import panda.domain.entities.User;
import panda.repository.interfaces.ReceiptRepository;
import panda.repository.util.EntityGetter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

import static panda.constants.Constants.MULTIPLE_RESULT;
import static panda.constants.Constants.PARAMETER_RECIPIENT;

public class ReceiptRepositoryImpl extends GenericRepositoryImpl<Receipt, String>
        implements ReceiptRepository {

    private final EntityManager entityManager;

    @Inject
    public ReceiptRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional findAllReceiptsByUser(User user) {
        return EntityGetter.getEntityByParameter(user,
                PARAMETER_RECIPIENT,
                this.entityManager,
                Receipt.class,
                MULTIPLE_RESULT);
    }
}
