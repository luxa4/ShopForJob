package ru.belyaev.shop.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.belyaev.shop.entity.Category;
import ru.belyaev.shop.entity.Producer;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.exception.InternalServerErrorException;
import ru.belyaev.shop.repositories.*;
import ru.belyaev.shop.service.ProductService;

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
            int offset = (page - 1) * limit;
            return productDao.listAllProduct();
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
            int offset = (page - 1) * limit;
            return productDao.listProductsByCategory(categoryUrl, offset, limit);
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
            return categoryDao.listAllCategories();
        } catch (Exception e) {
            throw new InternalServerErrorException("Cant execute sql query listAllCategories:" + e.getMessage(), e);
        }
    }

    @Override
    public List<Producer> listAllProducers() {
        try {
            return producerDao.listAllProducers();
        } catch (Exception e) {
            throw new InternalServerErrorException("Cant execute sql query listAllProducers:" + e.getMessage(), e);
        }
    }



//    @Override
//    public List<Product> ListProductBySearchForm(SearchForm searchForm, int page, int limit) {
//      try  {
//            int offset = (page - 1) * limit;
//            SearchQuery sq = buildSearchQuery(searchForm, " p.*, c.name as category, pr.name as producer FROM product p, category c, producer pr " );
//            sq.getSql().append(" offset ? limit ?");
//            sq.getParams().add(offset);
//            sq.getParams().add(limit);
//            return JDBCUtil.select(c, sq.getSql().toString() , productsResultSetHandler, sq.getParams().toArray());
//        } catch (SQLException e) {
//            throw new InternalServerErrorException("Can't execute sql-query - ListProductBySearchForm - :" + e.getMessage(), e);
//        }
//
//    }
//
//    protected SearchQuery buildSearchQuery (SearchForm searchForm, String selectField) {
//        StringBuilder sql = new StringBuilder("SELECT");
//        List<Object> params = new ArrayList<>();
//        sql.append(selectField).append(" WHERE c.id=p.id_category and pr.id=p.id_producer and (p.name ilike ? or p.description ilike ?) ");
//        params.add("%" + searchForm.getQuery() + "%");
//        params.add("%" + searchForm.getQuery() + "%");
//        JDBCUtil.pasteInSqlAndParams(sql , params , searchForm.getCategories(), "c.id=?");
//        JDBCUtil.pasteInSqlAndParams(sql , params , searchForm.getProducers(), "pr.id=?");
//        return new SearchQuery(sql, params);
//    }
//
//    @Override
//    public int countProductBySearchFrom(SearchForm searchForm) {
//        try (Connection c = dataSource.getConnection()) {
//            SearchQuery sq = buildSearchQuery(searchForm, " count(*) FROM product p, category c, producer pr " );
//            return JDBCUtil.select(c, sq.getSql().toString() , ResultSetHandlerFactory.getCountResultSet(), sq.getParams().toArray());
//        } catch (SQLException e) {
//            throw new InternalServerErrorException("Can't execute sql-query - countProductBySearchFrom - :" + e.getMessage(), e);
//        }
//    }


}
