package panda.repository;

import panda.domain.entities.Package;
import panda.domain.entities.Status;
import panda.repository.interfaces.PackageRepository;
import panda.repository.util.EntityGetter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

import static panda.constants.Constants.MULTIPLE_RESULT;
import static panda.constants.Constants.PARAMETER_STATUS;

public class PackageRepositoryImpl extends GenericRepositoryImpl<Package, String>
        implements PackageRepository {

    private final EntityManager entityManager;

    @Inject
    public PackageRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional findAllPackagesByStatus(Status status) {

        return EntityGetter.getEntityByParameter(status,
                PARAMETER_STATUS,
                this.entityManager,
                Package.class,
                MULTIPLE_RESULT);
    }
}
