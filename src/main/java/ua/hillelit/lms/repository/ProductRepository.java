package ua.hillelit.lms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.hillelit.lms.model.Product;

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
