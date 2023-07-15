package com.EcomBackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EcomBackend.Payload.ProductDto;
import com.EcomBackend.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        ProductDto createProduct = productService.createProduct(product);
        return new ResponseEntity<ProductDto>(createProduct, HttpStatus.CREATED);
    }

    @GetMapping("/view")
    public ResponseEntity<List<ProductDto>> viewAllProducts() {
        List<ProductDto> viewAllProducts = productService.viewAll();
        return new ResponseEntity<List<ProductDto>>(viewAllProducts, HttpStatus.OK);
    }

    @GetMapping("/view/{productId}")
    public ResponseEntity<ProductDto> viewProductById(@PathVariable int productId) {
        ProductDto viewProductById = productService.viewProductById(productId);
        return new ResponseEntity<ProductDto>(viewProductById, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{pId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int pId) {
        productService.deleteProduct(pId);
        return new ResponseEntity<String>("Product Deleted Successfully", HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable int productId, @RequestBody ProductDto product) {
        ProductDto updateProduct = productService.updateProduct(productId, product);
        return new ResponseEntity<ProductDto>(updateProduct, HttpStatus.ACCEPTED);
    }

}
