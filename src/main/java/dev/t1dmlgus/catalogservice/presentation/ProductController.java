package dev.t1dmlgus.catalogservice.presentation;


import dev.t1dmlgus.catalogservice.common.common.response.CommonResponse;
import dev.t1dmlgus.catalogservice.service.ProductCommand;
import dev.t1dmlgus.catalogservice.service.ProductInfo;
import dev.t1dmlgus.catalogservice.service.ProductService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class ProductController {

    private final Environment env;
    private final ProductService productService;

    public ProductController(Environment env, ProductService productService) {
        this.env = env;
        this.productService = productService;
    }

    @GetMapping("/health-check")
    public ResponseEntity<CommonResponse<String>> status(){

        String healthCheckMessage =
                String.format("Working Catalog-service on PORT %s", env.getProperty("local.server.port"));
        CommonResponse<String> commonResponse = CommonResponse.of(healthCheckMessage);
        return ResponseEntity.ok(commonResponse);
    }


    @PostMapping("/products")
    public ResponseEntity<CommonResponse<ProductInfo.ProductToken>> registerProduct(@RequestBody ProductDto.Register productDto){

        ProductCommand.Register productCommand = productDto.toCommand();
        ProductInfo.ProductToken productToken = productService.register(productCommand);
        return ResponseEntity.ok(CommonResponse.of(productToken, "상품이 등록되었습니다."));
    }

    @GetMapping("/products/{productToken}")
    public ResponseEntity<CommonResponse<ProductInfo.ProductDetail>> inquireProduct(@PathVariable String productToken){

        ProductInfo.ProductDetail productDetail = productService.inquire(productToken);
        return ResponseEntity.ok(CommonResponse.of(productDetail, "상품을 조회하였습니다."));
    }

    @GetMapping("/products")
    public ResponseEntity<CommonResponse<List<ProductInfo.ProductDetail>>> register(){

        List<ProductInfo.ProductDetail> productDetail = productService.inquireAll();
        return ResponseEntity.ok(CommonResponse.of(productDetail, "전체 상품을 조회하였습니다."));
    }


}
