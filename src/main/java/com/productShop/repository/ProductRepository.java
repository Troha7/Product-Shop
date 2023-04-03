package com.productShop.repository;

import com.productShop.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link ProductRepository}
 *
 * @author Dmytro Trotsenko on 1/25/23
 */

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    boolean existsProductById(Integer id);
    boolean existsProductByName(String name);

}
