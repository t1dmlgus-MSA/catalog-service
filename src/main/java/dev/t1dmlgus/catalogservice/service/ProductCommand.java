package dev.t1dmlgus.catalogservice.service;

import dev.t1dmlgus.catalogservice.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class ProductCommand {

    @ToString
    @Getter
    public static class Register {
        private final String productName;
        private final int stock;
        private final int price;

        @Builder
        public Register(String productName, int stock, int price) {
            this.productName = productName;
            this.stock = stock;
            this.price = price;
        }

        public static Register newInstance(String productName, int stock, int price){
            return Register.builder()
                    .productName(productName)
                    .stock(stock)
                    .price(price)
                    .build();
        }

        public Product toProduct() {
            return Product.newInstance(productName, stock, price);

        }
    }


}
