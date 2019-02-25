package employees.repository.interfaces;

import java.util.Optional;

public interface GenericRepository<E, K> {

    E save(E entity);

    Optional findAll();

    Optional findById(K id);

    void removeById(K id);
}
