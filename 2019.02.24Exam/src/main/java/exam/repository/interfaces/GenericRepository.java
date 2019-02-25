package exam.repository.interfaces;

import java.util.Optional;

public interface GenericRepository<E, ID> {

    E save(E entity);

    E update(E entity);

    int deleteById(ID id);

    Optional findAll();

    Optional findById(ID id);

    int size();
}
