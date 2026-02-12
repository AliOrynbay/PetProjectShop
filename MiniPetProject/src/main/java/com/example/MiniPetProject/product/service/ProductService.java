package com.example.MiniPetProject.product.service;

import com.example.MiniPetProject.product.api.ProductMapper;
import com.example.MiniPetProject.product.api.ProductRequestDto;
import com.example.MiniPetProject.product.api.ProductResponseDto;
import com.example.MiniPetProject.product.domain.Product;
import com.example.MiniPetProject.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponseDto> findAll(){
        log.info("Trying to find all products");
        List<Product> foundProducts = productRepository.findAll();
        log.info("Found {} products", foundProducts);
        return foundProducts.stream()
                .map(productMapper ::productToProductResponseDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto findById(Long id){
        log.info("Trying to find product by id {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        log.info("Found product {}", product);
        return productMapper.productToProductResponseDto(product);
    }

    public Product findProductById(Long id){
        log.info("Trying to find product by id {}", id);
        return productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productDto ){
        log.info("Trying to create product {}", productDto);
        if(productDto != null){
            Product product = productMapper.productRequestDtoToProduct(productDto);
            productRepository.save(product);
            log.info("Created product {}", product);
            return productMapper.productToProductResponseDto(product);
        }
        throw new RuntimeException("Product request is null");
    }

    @Transactional
    public ProductRequestDto updateProduct(ProductRequestDto productDto , Long id){
        log.info("Trying to update product {}", productDto);
        Product product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setUpdatedAt(Instant.now());
        productRepository.save(product);
        log.info("Updated product {}", product);
        return productMapper.productToProductRequestDto(product);
    }

    @Transactional
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
