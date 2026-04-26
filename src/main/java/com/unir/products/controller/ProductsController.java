package com.unir.products.controller;

import com.unir.products.model.pojo.Product;
import com.unir.products.model.request.CreateProductRequest;
import com.unir.products.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductsController {
  private final ProductsService service;

  @GetMapping("/products") // endpoints de la aplicación de tipo get
  public ResponseEntity<List<Product>> getProducts() {
    List<Product> products = service.getProducts();
    if (products != null) {
      return ResponseEntity.ok(products);
    } else {
      return ResponseEntity.ok(Collections.emptyList());
    }
  }

  @GetMapping("/products/{productId}") // endpoint get de path dinamico
  public ResponseEntity<Product> getProduct(@PathVariable String productId) {
    log.info("Request received for product {}", productId);
    Product product = service.getProduct(productId);
    if (product != null) {
      return ResponseEntity.ok(product);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/products/{productId}") //  end point delete con path dinamico
  public ResponseEntity<Product> deleteProduct(@PathVariable String productId) {
    Boolean removed = service.removeProduct(productId);
    if (Boolean.TRUE.equals(removed)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/products") // end point de tipo post
  public ResponseEntity<Product> getProduct(@RequestBody CreateProductRequest request) {
    Product createProduct = service.createProduct(request);
    if (createProduct != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}
