package ru.belyaev.shop.service;

import ru.belyaev.shop.entity.Category;
import ru.belyaev.shop.entity.Producer;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.form.SearchForm;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    //return list of product
    List<Product> listAllProduct(int page, int limit);

    int countAllProducts();

    // return list of Products if we know category
    List<Product> listProductsByCategory(String categoryUrl, int page, int limit);

    int countAllProductByCategory(String categoryUrl);

    // return list of all categories - edit sql query - Нужно для отображения категорий в списке блока ASIDE
    List<Category> listAllCategories();

    // return list of all Producers - edit sql query - Нужно для отображения производителей в списке поиска блока ASIDE
    List<Producer> listAllProducers();

    List<Product> ListProductBySearchForm (SearchForm searchForm, int page, int limit);

    int countProductBySearchFrom(SearchForm searchForm);


}
