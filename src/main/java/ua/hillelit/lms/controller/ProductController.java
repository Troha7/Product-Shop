package ua.hillelit.lms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.hillelit.lms.dto.ProductDto;
import ua.hillelit.lms.service.ProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link ProductController} is a class for making restful web service.
 * Main path "/products" and port "8080"
 *
 * @author Dmytro Trotsenko on 2/10/23
 */

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private static final int year = LocalDateTime.now().getYear();


    @GetMapping("add")
    public String addProduct(Model model) {
        log.info("GET request print add product page");
        model.addAttribute("year", year);
        model.addAttribute("product", new ProductDto());
        model.addAttribute("errorMsgs", new ArrayList<>());
        return "main/add";
    }
    @PostMapping("add")
    public String add(Model model, @ModelAttribute("product") ProductDto product) {
        log.info("POST request add new product");
        List<String> errors = addProductErrors(product);

        if(!errors.isEmpty()) {
            log.warn("Validation error, product is not added");
            model.addAttribute("errorMsgs", errors);
            return "main/add";
        }

        model.addAttribute("add",productService.create(product));
        log.info("Added new product");
        return "redirect:/products";
    }

    @GetMapping("{id}")
    public String removeById(@PathVariable int id){
        log.info("GET request remove the product");
        if (!productService.hasProductById(id)) {
            log.error("Product id={} is not exist", id);
            throw new IllegalArgumentException("Product id=" + id +" is not exist");
        }
        productService.removeById(id);
        log.info("Product is removed");
        return "redirect:/products";
    }

    @PostMapping
    public String getById(Model model, @ModelAttribute("product") ProductDto product) {
        log.info("POST request get product by id");
        Integer id = product.getId();
        if (!productService.hasProductById(id)) {
            log.warn("Product id={} is not exist", id);
            model.addAttribute("findErr","Product id=" + id +" is not exist");
            return "main/products";
        }
        model.addAttribute("products",productService.findById(id));
        log.info("Product by id is getting");
        return "main/products";
    }

    @GetMapping
    public String getProducts(Model model) {
        log.info("GET request print products service page");
        model.addAttribute("year", year);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("product", new ProductDto());
        log.info("Getting all products");
        return "main/products";
    }

    private static List<String> addProductErrors(ProductDto product) {
        List<String> addProductErrors = new ArrayList<>();

        if(product.getName() == null || product.getName().isEmpty()) {
            log.warn("Product name is required");
            addProductErrors.add("Product name is required");
        }

        if(product.getPrice() == null) {
            log.warn("Product price is required");
            addProductErrors.add("Product price is required");
        }
        return addProductErrors;
    }

}
