package panda.repository.interfaces;

import java.util.Optional;

public interface GenericRepository<E, ID> {

    E save(E entity);

    E update(E entity);

    Optional findAll();

    Optional findById(ID id);

    int size();
}
