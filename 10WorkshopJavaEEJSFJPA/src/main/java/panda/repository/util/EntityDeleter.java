package panda.repository.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import static panda.constants.Constants.CACHE_IGNORE;
import static panda.constants.Constants.CACHE_MODE;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 7.2.2019 г.
 * Time: 10:07 ч.
 */
public class EntityDeleter {

    public static <T, V> void deleteEntityByParameter(V parameterValue,
                                                   String parameterName,
                                                   EntityManager entityManager,
                                                   Class<T> aClass) {

        CacheClearer.clearCache(entityManager);

        CriteriaBuilder criteriaBuilder = entityManager
                .getCriteriaBuilder();
        CriteriaDelete<T> criteriaDelete = criteriaBuilder
                .createCriteriaDelete(aClass);
        Root<T> root = criteriaDelete.from(aClass);

        criteriaDelete.where(criteriaBuilder
                .equal(root.get(parameterName), parameterValue));

        entityManager.getTransaction().begin();

        Query query = entityManager
                .createQuery(criteriaDelete)
                .setHint(CACHE_MODE, CACHE_IGNORE);

        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}