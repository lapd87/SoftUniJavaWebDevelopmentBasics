package fdmc2.repository.util;

import javax.persistence.EntityManager;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.2.2019 г.
 * Time: 13:28 ч.
 */
public class CacheClearer {

    public static void clearCache(EntityManager entityManager) {
        entityManager
                .getEntityManagerFactory()
                .getCache()
                .evictAll();

        entityManager.clear();
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
    }
}