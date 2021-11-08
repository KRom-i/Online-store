package ru.gb.online.store.services;

import ru.gb.online.store.entities.Category;
import ru.gb.online.store.entities.Product;
import ru.gb.online.store.repositories.ProductRepository;
import ru.gb.online.store.utils.ParamsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


@Service
public class ProductService {

    @Autowired
    private ParamsPage paramsPage;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(Integer page, Integer size,
                                        String title, String min, String max) {

        paramsPage.setPage (page);
        paramsPage.setSize (size);
        paramsPage.setTitle (title);
        paramsPage.setMin (min);
        paramsPage.setMax (max);

        return productRepository.findAll (paramsPage.getSpecification (),
                PageRequest.of (paramsPage.getPage () - 1, paramsPage.getSize ())
        );

    }

    public Product getProductById (Long id) {
        return productRepository.findById (id).get ();
    }

    public ParamsPage getParamsPage () {
        return paramsPage;
    }

    public boolean save (Product product) {

        if (productRepository.findOneByTitle (product.getTitle ()) != null){
            return false;
        }

        product.setCategory (categoryService.findById (product.getCategory ().getId ()));
        product.setVendorCode (getNewVendorCode());
        product.setCreateAt (LocalDateTime.now ());

        productRepository.save (product);
        return true;
    }

    private String getNewVendorCode(){
        Long lastId = productRepository.findTopByOrderByIdDesc ().getId ();
        return String.format("%08d", lastId);
    }

    public Page<Product> getProductsLastInSize(int size){
        return productRepository.findByOrderByIdDesc (Pageable.ofSize (size));
    }

    public List<Category> getAllCategories () {
        return categoryService.getAllCategories ();
    }
}
