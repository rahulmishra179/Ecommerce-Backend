package com.EcomBackend.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EcomBackend.Exception.ResourceNotFoundException;
import com.EcomBackend.Model.Product;
import com.EcomBackend.Payload.ProductDto;
import com.EcomBackend.Repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // create product
    public ProductDto createProduct(ProductDto product) {
        // productDto to product
        Product entity = toEntity(product);
        Product create = productRepository.save(entity);
        // product to productDto
        ProductDto dto = toDto(create);
        return dto;
    }

    // view all product
    public List<ProductDto> viewAll() {
        List<Product> viewAllProduct = productRepository.findAll();
        List<ProductDto> viewAllProductDto = viewAllProduct.stream().map(product -> this.toDto(product))
                .collect(Collectors.toList());
        return viewAllProductDto;
    }

    // view product by id
    public ProductDto viewProductById(int productId) {
        Product viewAllProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(+productId + " " + "Product is Not Found"));
        ProductDto viewAllProductDto = toDto(viewAllProduct);
        return viewAllProductDto;
    }

    //delete product
    public void deleteProduct(int pId) {
        Product product = productRepository.findById(pId)
                .orElseThrow(() -> new ResourceNotFoundException(+pId + " " + "Product is Not Found"));
        productRepository.delete(product);
    }

    //update product
    public ProductDto updateProduct(int productId, ProductDto product) {
        Product oldProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(+productId + " " + "Product is Not updated"));
        oldProduct.setLive(product.isLive());
        oldProduct.setProduct_desc(product.getProduct_desc());
        oldProduct.setProduct_imageName(product.getProduct_imageName());
        oldProduct.setProduct_price(product.getProduct_price());
        oldProduct.setProduct_quantity(product.getProduct_quantity());
        oldProduct.setStock(product.isStock());
        oldProduct.setProduct_name(product.getProduct_name());
        Product updateProduct = productRepository.save(oldProduct);
        ProductDto updateProductDto=toDto(updateProduct);
        return updateProductDto;
    }

    public Product toEntity(ProductDto pDto) {
        Product p = new Product();
        p.setProduct_id(pDto.getProduct_id());
        p.setLive(pDto.isLive());
        p.setProduct_desc(pDto.getProduct_desc());
        p.setProduct_imageName(pDto.getProduct_imageName());
        p.setProduct_price(pDto.getProduct_price());
        p.setProduct_quantity(pDto.getProduct_quantity());
        p.setStock(pDto.isStock());
        p.setProduct_name(pDto.getProduct_name());
        return p;
    }

    public ProductDto toDto(Product p) {
        ProductDto pDto = new ProductDto();
        pDto.setProduct_id(p.getProduct_id());
        pDto.setLive(p.isLive());
        pDto.setProduct_desc(p.getProduct_desc());
        pDto.setProduct_imageName(p.getProduct_imageName());
        pDto.setProduct_price(p.getProduct_price());
        pDto.setProduct_quantity(p.getProduct_quantity());
        pDto.setStock(p.isStock());
        pDto.setProduct_name(p.getProduct_name());
        return pDto;
    }

}
