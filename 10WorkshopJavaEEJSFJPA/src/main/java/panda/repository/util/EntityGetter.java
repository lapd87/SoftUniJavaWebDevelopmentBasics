package panda.repository.util;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

import static panda.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 7.2.2019 г.
 * Time: 10:07 ч.
 */
public class EntityGetter {

    public static <T, V> Optional getEntityByParameter(V parameterValue,
                                                       String parameterName,
                                                       EntityManager entityManager,
                                                       Class<T> aClass,
                                                       String returnType) {

        CacheClearer.clearCache(entityManager);

        CriteriaBuilder criteriaBuilder = entityManager
                .getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder
                .createQuery(aClass);
        Root<T> root = criteriaQuery.from(aClass);

        criteriaQuery.select(root);

        if (parameterValue != null) {
            criteriaQuery.where(criteriaBuilder
                    .equal(root.get(parameterName), parameterValue));
        }

        try {
            TypedQuery<T> typedQuery = entityManager
                    .createQuery(criteriaQuery)
                    .setHint(CACHE_MODE, CACHE_IGNORE);

            if (returnType.equals(SINGLE_RESULT)) {

                return Optional.ofNullable(typedQuery
                        .getSingleResult());

            } else if (returnType.equals(MULTIPLE_RESULT)) {

                return Optional.ofNullable(typedQuery
                        .getResultList());

            } else {
                throw new NoResultException();
            }
        } catch (NoResultException ignored) {
            return Optional.empty();
        }
    }
}