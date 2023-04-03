package com.productShop;

import com.productShop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import com.productShop.dto.ProductDto;
import com.productShop.dto.UserInfoDto;
import com.productShop.service.ProductService;

import static com.productShop.model.UserRole.ADMIN;
import static com.productShop.model.UserRole.USER;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class ProductShopApp {

    private final ProductService productService;
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ProductShopApp.class, args);
    }

    /**
     * Run application after all initialization steps.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        var admin = new UserInfoDto(null, "admin", "admin", ADMIN);
        var user = new UserInfoDto(null, "user", "user", USER);
        userService.createUser(admin);
        userService.createUser(user);

    }

}
