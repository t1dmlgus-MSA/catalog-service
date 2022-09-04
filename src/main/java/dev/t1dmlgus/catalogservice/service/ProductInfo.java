package dev.t1dmlgus.catalogservice.service;

import dev.t1dmlgus.catalogservice.domain.Product;
import lombok.Builder;
import lombok.Getter;

public class ProductInfo {

    @Getter
    public static class ProductToken {

        private final String productToken;

        @Builder
        private ProductToken(String productToken) {
            this.productToken = productToken;
        }

        public static ProductToken newInstance(Product product){
            return ProductToken.builder()
                    .productToken(product.getProductToken())
                    .build();
        }
    }

    @Getter
    public static class ProductDetail {

        private final String productToken;
        private final String productName;
        private final int price;

        @Builder
        public ProductDetail(String productToken, String productName, int price) {
            this.productToken = productToken;
            this.productName = productName;
            this.price = price;
        }

        public static ProductDetail newInstance(Product product){
            return ProductDetail.builder()
                    .productToken(product.getProductToken())
                    .productName(product.getProductName())
                    .price(product.getPrice())
                    .build();
        }
    }
}