package com.example.MiniPetProject.product.api;

import com.example.MiniPetProject.product.domain.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDto productToProductResponseDto(Product product);
    ProductRequestDto productToProductRequestDto(Product product);
    Product productRequestDtoToProduct(ProductRequestDto productDto);
    Product productResponseDtoToProduct(ProductResponseDto productDto);

}
