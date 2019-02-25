package chushka.repository.interfaces;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 31.1.2019 г.
 * Time: 19:24 ч.
 */
public interface GenericRepo<E, K> {

    E save(E entity);

    E findById(K id);

    List<E> findAll();

}
