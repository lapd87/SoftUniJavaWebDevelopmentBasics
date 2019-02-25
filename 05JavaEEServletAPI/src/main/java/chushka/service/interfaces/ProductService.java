package chushka.service.interfaces;

import chushka.domain.models.service.ProductServiceModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 31.1.2019 г.
 * Time: 20:01 ч.
 */
public interface ProductService {

    void saveProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProducts();

    ProductServiceModel findProductByName(String name);
}
