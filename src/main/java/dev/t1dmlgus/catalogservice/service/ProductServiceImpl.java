package dev.t1dmlgus.catalogservice.service;


import dev.t1dmlgus.catalogservice.domain.Product;
import dev.t1dmlgus.catalogservice.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductInfo.ProductToken register(ProductCommand.Register productCommand) {

        Product product = productCommand.toProduct();
        Product save = productRepository.save(product);
        return ProductInfo.ProductToken.newInstance(save);
    }

    @Override
    public ProductInfo.ProductDetail inquire(String productToken) {

        Product product = productRepository.findByProductToken(productToken)
                .orElseThrow(() -> new RuntimeException("해당 상품을 조회할 수 없습니다."));
        return ProductInfo.ProductDetail.newInstance(product);
    }

    @Override
    public List<ProductInfo.ProductDetail> inquireAll() {

        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductInfo.ProductDetail::newInstance)
                .collect(Collectors.toList());
    }
}
