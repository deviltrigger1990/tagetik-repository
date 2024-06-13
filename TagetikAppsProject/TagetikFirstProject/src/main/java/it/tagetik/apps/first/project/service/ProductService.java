package it.tagetik.apps.first.project.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductService {

    @GetMapping("{productId}")
    String all() {
        return "ok";
    }

}
