package dev.t1dmlgus.catalogservice.service;

import java.util.List;

public interface ProductService {
    ProductInfo.ProductToken register(ProductCommand.Register productCommand);
    ProductInfo.ProductDetail inquire(String productToken);
    List<ProductInfo.ProductDetail> inquireAll();

}
