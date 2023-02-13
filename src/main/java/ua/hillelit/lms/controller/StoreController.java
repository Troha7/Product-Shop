package ua.hillelit.lms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.hillelit.lms.service.ProductService;

import java.time.LocalDateTime;

/**
 * {@link StoreController} is a class for making restful web service.
 * Main path "/" and port "8080".
 *
 * @author Dmytro Trotsenko on 2/10/23
 */

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class StoreController {

    private final ProductService productService;
    private static final int year = LocalDateTime.now().getYear();

    @GetMapping("home")
    public String welcome(Model model) {
        log.info("Home page");
        model.addAttribute("year", year);
        model.addAttribute("products", productService.findAll());
        log.info("GET request print all products");
        return "main/home";
    }


    @GetMapping("about")
    public String about(Model model) {
        log.info("GET request print about project page");
        model.addAttribute("year", year);
        return "main/about";
    }


}
