package chushka.service;

import chushka.domain.entities.Product;
import chushka.domain.entities.ProductType;
import chushka.domain.models.service.ProductServiceModel;
import chushka.repository.interfaces.ProductRepo;
import chushka.service.interfaces.ProductService;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 31.1.2019 г.
 * Time: 20:03 ч.
 */
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepo productRepo,
                              ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper
                .map(productServiceModel, Product.class);
        product.setType(ProductType
                .valueOf(productServiceModel.getType()));

        this.productRepo.save(product);
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {

        return this.productRepo.findAll()
                .stream()
                .map(product -> {
                    ProductServiceModel productServiceModel = this.modelMapper
                            .map(product, ProductServiceModel.class);
                    productServiceModel.setName(product.getName());
                    return productServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel findProductByName(String name) {

        Product product = this.productRepo.findByName(name);

        ProductServiceModel productServiceModel = this.modelMapper
                .map(product, ProductServiceModel.class);
        productServiceModel.setType(product.getType().name());

        return productServiceModel;
    }
}