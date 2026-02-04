package com.example.MiniPetProject.product.service;

import com.example.MiniPetProject.product.api.ProductMapper;
import com.example.MiniPetProject.product.api.ProductRequestDto;
import com.example.MiniPetProject.product.api.ProductResponseDto;
import com.example.MiniPetProject.product.domain.Product;
import com.example.MiniPetProject.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponseDto> findAll(){
        List<Product> foundProducts = productRepository.findAll();
        return foundProducts.stream()
                .map(productMapper ::productToProductResponseDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto findById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        return productMapper.productToProductResponseDto(product);
    }

    public Product findProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productDto ){
        if(productDto != null){
            Product product = productMapper.productRequestDtoToProduct(productDto);
            productRepository.save(product);
            return productMapper.productToProductResponseDto(product);
        }
        throw new RuntimeException("Product request is null");
    }

    @Transactional
    public ProductRequestDto updateProduct(ProductRequestDto productDto , Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setUpdatedAt(Instant.now());
        productRepository.save(product);
        return productMapper.productToProductRequestDto(product);
    }

    @Transactional
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
