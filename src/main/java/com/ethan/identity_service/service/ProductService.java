package com.ethan.identity_service.service;

import com.ethan.identity_service.dto.request.product.ProductCreateRequest;
import com.ethan.identity_service.entity.ProductEntity;
import com.ethan.identity_service.mapper.ProductMapper;
import com.ethan.identity_service.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductEntity createProduct(ProductCreateRequest request) {
        var productData = productMapper.toProduct(request);
        return productRepository.save(productData);
    }

    public List<ProductEntity> createProductBulk(List<ProductEntity> productDatas) {
        return productRepository.saveAll(productDatas);
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity getProductDetails(String id) {
      return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductEntity updateProduct(String id, ProductEntity productData) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(productData.getProductName());
        product.setProductDescription(productData.getProductDescription());
        product.setProductPrice(productData.getProductPrice());
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    public void bulkDelete(List<String> ids) {
        productRepository.deleteAllById(ids);
    }

}


