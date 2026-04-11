package com.humber.test4ims.controller;

import com.humber.test4ims.entity.Product;
import com.humber.test4ims.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("totalProducts", productService.getTotalProducts());
        model.addAttribute("totalStock", productService.getTotalStock());
        return "products/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("formTitle", "Add New Product");
        model.addAttribute("action", "/products/save");
        return "products/form";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Add New Product");
            model.addAttribute("action", "/products/save");
            return "products/form";
        }
        productService.addProduct(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product added successfully.");
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("formTitle", "Update Product");
        model.addAttribute("action", "/products/update/" + id);
        return "products/form";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Update Product");
            model.addAttribute("action", "/products/update/" + id);
            return "products/form";
        }
        productService.updateProduct(id, product);
        redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully.");
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully.");
        return "redirect:/products";
    }

    @PostMapping("/stock/{id}")
    public String updateStock(@PathVariable Long id,
                              @RequestParam Integer stock,
                              RedirectAttributes redirectAttributes) {
        productService.updateStock(id, stock);
        redirectAttributes.addFlashAttribute("successMessage", "Stock updated successfully.");
        return "redirect:/products";
    }
}
