package chushka.repository.interfaces;

import chushka.domain.entities.Product;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 31.1.2019 г.
 * Time: 19:25 ч.
 */
public interface ProductRepo extends GenericRepo<Product, String> {

    Product findByName(String name);

}