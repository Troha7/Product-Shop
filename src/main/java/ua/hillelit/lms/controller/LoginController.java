package ua.hillelit.lms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * {@link LoginController} is a class for making restful web service.
 * Main path "/products" and port "8080"
 *
 * @author Dmytro Trotsenko on 2/4/23
 */

@Controller
@RequestMapping("/")
@Slf4j
public class LoginController {

    private static final int year = LocalDateTime.now().getYear();

    @GetMapping("login")
    public String login(Model model) {
        log.info("GET request print login page");
        model.addAttribute("year", year);
        return "login";
    }

    @GetMapping("403")
    public String error403(Model model) {
        log.info("GET request print error 403 page");
        model.addAttribute("year", year);
        return "403Error";
    }

}
