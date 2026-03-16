package re.projects.presentation;

import re.projects.service.ProductService;

public class ProductView {
    // Gọi tới service
    private ProductService productService;

    public ProductView(ProductService productService) {
        this.productService = productService;
    }
}
