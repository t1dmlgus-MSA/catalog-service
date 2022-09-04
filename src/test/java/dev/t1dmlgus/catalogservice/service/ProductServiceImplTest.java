package dev.t1dmlgus.catalogservice.service;

import dev.t1dmlgus.catalogservice.domain.Product;
import dev.t1dmlgus.catalogservice.domain.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;


    @Test
    void register_product_return_productToken_start_with_P() {
        // given
        ProductCommand.Register productCommand =
                ProductCommand.Register.newInstance(
                        "그리스 로마 신화 8 - 오르페우스의 사랑",
                        100,
                        1_500);
        Product product = productCommand.toProduct();
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);

        // when
        ProductInfo.ProductToken productToken = productService.register(productCommand);

        // then
        Assertions.assertThat(productToken.getProductToken().substring(0, 1)).isEqualTo("P");
    }

}