package ru.belyaev.shop.service.impl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.belyaev.shop.entity.Category;
import ru.belyaev.shop.entity.Producer;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.exception.InternalServerErrorException;
import ru.belyaev.shop.form.SearchForm;
import ru.belyaev.shop.repositories.*;
import ru.belyaev.shop.service.ProductService;


import java.util.ArrayList;
import java.util.List;

@Service
class ProductServiceImpl implements ProductService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProducerDao producerDao;




    @Override
    public List<Product> listAllProduct(int page, int limit) {
        try {
//            int offset = (page - 1) * limit;
            Pageable pageable = PageRequest.of(page-1, limit);
            return productDao.listAllProduct(pageable);
        } catch (Exception e) {
            throw new InternalServerErrorException("Can't execute sql query listAllProduct: " + e.getMessage(), e);
        }
    }

    @Override
    public int countAllProducts() {
        try  {
           return productDao.countAllProducts();
        } catch (Exception e) {
            throw new InternalServerErrorException("Can't execute sql query countAllProducts: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> listProductsByCategory(String categoryUrl, int page, int limit) {
        try {
            Pageable pageable = PageRequest.of(page-1, limit);
            return productDao.listProductsByCategory(categoryUrl, pageable);
        } catch (Exception e) {
            throw new InternalServerErrorException("Cant execute sql query listProductsByCategory:" + e.getMessage(), e);
        }
    }

    @Override
    public int countAllProductByCategory(String categoryUrl) {
        try {
            return productDao.countAllProductByCategory(categoryUrl);
        } catch (Exception e) {
            throw new InternalServerErrorException("Cant execute sql query countAllProductByCategory:" + e.getMessage(), e);
        }
    }

    @Override
    public List<Category> listAllCategories() {
        try {
            return categoryDao.findAllByOrderByName();
        } catch (Exception e) {
            throw new InternalServerErrorException("Cant execute sql query listAllCategories:" + e.getMessage(), e);
        }
    }

    @Override
    public List<Producer> listAllProducers() {
        try {
            return producerDao.findAllByOrderByName();
        } catch (Exception e) {
            throw new InternalServerErrorException("Cant execute sql query listAllProducers:" + e.getMessage(), e);
        }
    }



    @Override
    public List<Product> ListProductBySearchForm(SearchForm searchForm, int page, int limit, List<Integer> allCategoriesId, List<Integer> allProducersId) {
        try  {
            List<Integer> categoriesId;
            List<Integer> producersId;

            if (searchForm.isCategoriesNotEmpty()) {
                categoriesId = searchForm.getCategories();
            } else {
                categoriesId = allCategoriesId;
            }

            if (searchForm.isProducersNotEmpty()) {
                producersId = searchForm.getProducers();
            } else {
                producersId = allProducersId;
            }

            Pageable pageable = PageRequest.of(page-1, limit);
            return productDao.ListProductBySearchForm(searchForm.getQuery(), producersId, categoriesId, pageable);
        } catch (Exception e) {
            throw new InternalServerErrorException("Can't execute sql-query - ListProductBySearchForm - :" + e.getMessage(), e);
        }

    }


    @Override
    public int countProductBySearchFrom(SearchForm searchForm, List<Integer> allCategoriesId, List<Integer> allProducersId) {
        try  {
            List<Integer> categoriesId;
            List<Integer> producersId;

            if (searchForm.isCategoriesNotEmpty()) {
                categoriesId = searchForm.getCategories();
            } else {
                categoriesId = allCategoriesId;
            }

            if (searchForm.isProducersNotEmpty()) {
                producersId = searchForm.getProducers();
            } else {
                producersId = allProducersId;
            }

            return productDao.countProductBySearchFrom(searchForm.getQuery(), producersId, categoriesId);
        } catch (Exception e) {
            throw new InternalServerErrorException("Can't execute sql-query - countProductBySearchFrom - :" + e.getMessage(), e);
        }
    }
    @Override
    public List<Integer> getListCategoriesId() {
        List<Category> categories = categoryDao.findAllByOrderByName();
        List<Integer> categorieId = new ArrayList<>();

        for (int i=0; i <  categories.size(); i++) {
            categorieId.add(categories.get(i).getId());
        }
        return categorieId;
    }



    @Override
    public List<Integer> getListProducersId() {
        List<Producer> producers = producerDao.findAllByOrderByName();
        List<Integer> producersId = new ArrayList<>();

        for (int i=0; i < producers.size(); i++) {
            producersId.add(producers.get(i).getId());
        }
        return producersId;
    }



}
