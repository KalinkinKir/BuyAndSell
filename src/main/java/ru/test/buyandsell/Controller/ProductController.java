package ru.test.buyandsell.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.test.buyandsell.Models.Product;
import ru.test.buyandsell.Services.ProductService;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name = "Контроллер товаров", description = "Отвечает за создание/удаление товаров, показывает страницу с конкретным товаром")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Страница с конкретным товаром")
    @GetMapping("/{id}")
    public String productInfo(@PathVariable Long id, Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product-info";
    }

    @Operation(summary = "Создание нового товара")
    @PostMapping("/creation")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/";
    }

    @Operation(summary = "Удаление существующего товара")
    @DeleteMapping("/deletingProduct/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
