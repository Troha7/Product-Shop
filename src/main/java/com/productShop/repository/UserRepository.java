package com.productShop.repository;

import com.productShop.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * {@link UserRepository}
 *
 * @author Dmytro Trotsenko on 1/25/23
 */

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Integer> {

    Optional<UserInfo> findFirstByName(String name);

    boolean existsUserByName(String name);

}
