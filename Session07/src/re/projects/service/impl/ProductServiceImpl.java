package re.projects.service.impl;

import re.projects.dao.ProductDao;
import re.projects.service.ProductService;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }
}
