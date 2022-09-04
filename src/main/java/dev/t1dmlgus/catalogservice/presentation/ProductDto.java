package dev.t1dmlgus.catalogservice.presentation;

import dev.t1dmlgus.catalogservice.service.ProductCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

public class ProductDto {

    @ToString
    @NoArgsConstructor
    @Getter
    public static class Register {

        @NotBlank(message = "상품명을 입력해주세요")
        private String productName;
        @NotBlank(message = "재고를 입력해주세요")
        private int stock;
        @NotBlank(message = "가격을 입력해주세요")
        private int price;

        @Builder
        public Register(String productName, int stock, int price) {
            this.productName = productName;
            this.stock = stock;
            this.price = price;
        }

        public ProductCommand.Register toCommand() {
            return ProductCommand.Register.newInstance(productName, stock, price);
        }
    }
}