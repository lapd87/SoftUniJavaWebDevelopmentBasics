package employees.repository;

import employees.domain.entities.Employee;
import employees.repository.interfaces.EmployeeRepository;
import employees.repository.util.EntityDeleter;
import employees.repository.util.EntityGetter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

import static employees.constants.Constants.*;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee save(Employee entity) {
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
                Employee.class,
                MULTIPLE_RESULT);
    }

    @Override
    public Optional findById(String id) {
        return EntityGetter.getEntityByParameter(id,
                PARAMETER_ID,
                this.entityManager,
                Employee.class,
                SINGLE_RESULT);
    }

    @Override
    public void removeById(String id) {
        EntityDeleter.deleteEntityByParameter(id,
                PARAMETER_ID,
                this.entityManager,
                Employee.class);
    }
}
