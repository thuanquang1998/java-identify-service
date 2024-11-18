package com.ethan.identity_service.controller;

import com.ethan.identity_service.dto.request.product.ProductCreateRequest;
import com.ethan.identity_service.entity.ProductEntity;
import com.ethan.identity_service.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @PostMapping
    public ProductEntity createProduct(@RequestBody ProductCreateRequest productData) {
        return productService.createProduct(productData);
    }

    @PostMapping("/create-bulk")
    public List<ProductEntity> createProducts(@RequestBody List<ProductEntity> productDatas) {
        return productService.createProductBulk(productDatas);
    }

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductEntity getProductDetails(@PathVariable String id) {
        return productService.getProductDetails(id);
    }

    @PutMapping("/{id}")
    public ProductEntity updateProduct(@PathVariable String id, @RequestBody ProductEntity productData) {
        return productService.updateProduct(id, productData);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @PostMapping("/bulk-delete")
    public void bulkDelete(@RequestBody List<String> ids) {
        productService.bulkDelete(ids);
    }
}
