package ru.gb.online.store.controllers;

import ru.gb.online.store.entities.Product;
import ru.gb.online.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String adminHome() {
        return "admin";
    }

    @GetMapping("/productForm")
    public String formProduct(Model model) {

        model.addAttribute ("product", new Product ());
        model.addAttribute ("categories", productService.getAllCategories ());
        model.addAttribute ("productsAdded", productService.getProductsLastInSize (10));

        return "product_form";
    }

    @PostMapping("/addNewProduct")
    public String addNewProduct(Model model, @ModelAttribute("product") Product saveProduct) {

        if (saveProduct == null){
            return "redirect:/product_form";
        }

        if (productService.save(saveProduct)){
            model.addAttribute ("message", "Товар добавлен в базу данных");
            model.addAttribute ("product", new Product ());

        } else {
            model.addAttribute ("error", "Ошибка при сохранении товара");
            model.addAttribute ("product", saveProduct);
        }

        model.addAttribute ("categories", productService.getAllCategories ());
        model.addAttribute ("productsAdded", productService.getProductsLastInSize (10));

        return "product_form";
    }


}
