package dev.t1dmlgus.catalogservice.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.t1dmlgus.catalogservice.domain.Product;
import dev.t1dmlgus.catalogservice.service.ProductCommand;
import dev.t1dmlgus.catalogservice.service.ProductInfo;
import dev.t1dmlgus.catalogservice.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ProductService userService;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void category_service_on_status() throws Exception {

        // when
        ResultActions resultActions = mockMvc.perform(
                get("/catalog-service/health-check")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        );

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print()
                );
    }


    @Test
    void register_product_return_productToken_exists() throws Exception {

        //given
        ProductDto.Register productDto = ProductDto.Register.builder()
                .productName("그리스 로마 신화 8 - 오르페우스의 사랑")
                .stock(100)
                .price(1_500)
                .build();

        Product product = productDto.toCommand().toProduct();
        ProductInfo.ProductToken productToken = ProductInfo.ProductToken.newInstance(product);

        String json = new ObjectMapper().writeValueAsString(productDto);
        given(userService.register(any(ProductCommand.Register.class)))
                .willReturn(productToken);

        //when
        ResultActions resultActions = mockMvc.perform(
                post("/catalog-service/products")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        );

        //then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.message").value("상품이 등록되었습니다."))

                // 추후, startsWith와 같은 검증 테스트 필요
                .andExpect(jsonPath("$.data.productToken").exists())
                .andDo(print()
                );

        verify(userService).register(any(ProductCommand.Register.class));


    }
}