package com.productShop.service;

import com.productShop.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.productShop.dto.ProductDto;
import com.productShop.repository.ProductRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * {@link ProductService}
 *
 * @author Dmytro Trotsenko on 1/25/23
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    /**
     * Create new entity {@link Product} and save row with data in database
     *
     * @param productDto object with data
     * @return productDto
     */
    public ProductDto create(ProductDto productDto) {
        log.info("Start create product");
        if (!hasProductByName(productDto.getName())) {
            Product product = objectMapper.convertValue(productDto, Product.class);
            productRepository.save(product);
            productDto.setId(product.getId());
        } else {
            log.warn("Product name={} already created", productDto.getName());
        }
        log.info("Product: {} is created", productDto);
        return productDto;
    }

    /**
     * Remove product by id
     *
     * @param id product id
     * @return productDto
     */
    public ProductDto removeById(Integer id) {
        log.info("Start remove product by id={}", id);
        ProductDto productDto = findById(id);
        productRepository.delete(objectMapper.convertValue(productDto, Product.class));
        log.info("Product: {} is removed", productDto);
        return productDto;
    }

    /**
     * Find product by id
     *
     * @param id product id
     * @return ProductDto
     * @throws EntityNotFoundException Product with id wasn't found
     */
    public ProductDto findById(Integer id) {
        log.info("Start find product by id={}", id);
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.error("Product with id={} wasn't found", id);
                    return new EntityNotFoundException("Product with id=" + id + " wasn't found");
                });
        ProductDto productDto = objectMapper.convertValue(product, ProductDto.class);
        log.info("Product: {} is founded", productDto);
        return productDto;
    }

    /**
     * Find all products
     *
     * @return List<ProductDto>
     */
    public List<ProductDto> findAll() {
        log.info("Start find all products");
        List<Product> products = (List<Product>) productRepository.findAll();
        log.info("All products is founded");
        return products.stream()
                .map(p -> objectMapper.convertValue(p, ProductDto.class))
                .collect(toList());
    }

    /**
     * Product by name exist
     *
     * @param name product name
     * @return true if exist
     */
    public boolean hasProductByName(String name) {
        return productRepository.existsProductByName(name);
    }

    /**
     * Product by id exist
     *
     * @param id product name
     * @return true if exist
     */
    public boolean hasProductById(Integer id) {
        return productRepository.existsProductById(id);
    }

}
