package com.productShop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link Ping} is a class for making restful web service.
 * Main path "/ping" and port "8080".
 *
 * @author Dmytro Trotsenko on 2/1/23
 */

@RestController
@RequestMapping("ping")
@RequiredArgsConstructor
@Slf4j
public class Ping {

    /**
     * GET request for getting ping status.
     *
     * @return ping status
     */
    @GetMapping
    public String ping() {
        log.info("GET request print ping page");
        return "OK";
    }

}
