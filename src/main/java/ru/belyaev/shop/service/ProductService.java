package ru.belyaev.shop.service;

import ru.belyaev.shop.entity.Category;
import ru.belyaev.shop.entity.Producer;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.form.SearchForm;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {


    // return list of all Producers - edit sql query - Нужно для отображения производителей в списке поиска блока ASIDE
    List<Producer> listAllProducers();


    // return list of Products if we know category
    List<Product> listProductsByCategory(String categoryUrl, int page, int limit);

    int countAllProductByCategory(String categoryUrl);


    List<Category> listAllCategories();


    List<Product> ListProductBySearchForm (SearchForm searchForm, int page, int limit, List<Integer> allCategoriesId, List<Integer> allProducersId);

    int countProductBySearchFrom(SearchForm searchForm, List<Integer> allCategoriesId, List<Integer> allProducersId);

    List<Product> listAllProduct(int page, int limit);

    int countAllProducts();

    List<Integer> getListCategoriesId();

    List<Integer> getListProducersId();

}
