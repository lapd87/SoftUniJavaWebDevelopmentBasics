package chushka.repository;

import chushka.domain.entities.Product;
import chushka.repository.interfaces.ProductRepo;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static chushka.constants.Constants.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 31.1.2019 г.
 * Time: 19:29 ч.
 */
public class ProductRepoImpl implements ProductRepo {

    private EntityManager entityManager;

    public ProductRepoImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory(PERSISTENCE_NAME)
                .createEntityManager();
    }

    @Override
    public Product save(Product entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Product findById(String id) {
        return getProductByParameter(id, PARAMETER_ID);
    }

    @Override
    public List<Product> findAll() {
        CriteriaBuilder criteriaBuilder = this.entityManager
                .getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder
                .createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        criteriaQuery.select(root);

        return this.entityManager
                .createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public Product findByName(String name) {
        return getProductByParameter(name, PARAMETER_NAME);
    }

    private Product getProductByParameter(String name, String parameterName) {
        CriteriaBuilder criteriaBuilder = this.entityManager
                .getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder
                .createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get(parameterName), name));

        return this.entityManager
                .createQuery(criteriaQuery)
                .getSingleResult();
    }
}