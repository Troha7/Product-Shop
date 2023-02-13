package ua.hillelit.lms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ua.hillelit.lms.dto.ProductDto;
import ua.hillelit.lms.dto.UserInfoDto;
import ua.hillelit.lms.service.ProductService;
import ua.hillelit.lms.service.UserService;

import static ua.hillelit.lms.model.UserRole.ADMIN;
import static ua.hillelit.lms.model.UserRole.USER;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class MyStoreApp {

    private final ProductService productService;
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(MyStoreApp.class, args);
    }

    /**
     * Run application after all initialization steps.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        var milk = new ProductDto(null, "Milk", 40.75);
        var bread = new ProductDto(null, "Bread", 18.50);
        var cola = new ProductDto(null, "Coca-Cola", 50.0);
        var sugar = new ProductDto(null, "Sugar", 35.60);
        productService.create(milk);
        productService.create(bread);
        productService.create(cola);
        productService.create(sugar);

        var admin = new UserInfoDto(null, "admin", "admin", ADMIN);
        var user = new UserInfoDto(null, "user", "user", USER);
        userService.createUser(admin);
        userService.createUser(user);

    }

}
