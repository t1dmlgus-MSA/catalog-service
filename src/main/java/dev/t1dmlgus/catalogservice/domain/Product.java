package dev.t1dmlgus.catalogservice.domain;


import dev.t1dmlgus.catalogservice.common.common.util.AbstractEntity;
import dev.t1dmlgus.catalogservice.common.common.util.TokenUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "products")
@Entity
public class Product extends AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productToken;

    private String productName;

    private int stock;

    private int price;


    @Builder
    private Product(String productName, int stock, int price) {
        this.productName = productName;
        this.stock = stock;
        this.price = price;
        createUserToken();
    }

    public static Product newInstance(String productName, int stock, int price){
        return Product.builder()
                .productName(productName)
                .stock(stock)
                .price(price)
                .build();
    }


    private void createUserToken() {
        this.productToken = TokenUtil.generateToken("product");
    }
}

