/*
 * Created by Vologda Developer
 * Date: 20.02.2020
 * Time: 15:11
 */

package ru.belyaev.shop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.form.SearchForm;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p")
    List<Product> listAllProduct(Pageable pageable) ;

    @Query("SELECT count(p) FROM Product p")
    int countAllProducts();

    @Query("SELECT p FROM Product p WHERE p.category.url=?1")
    List<Product> listProductsByCategory(String categoryUrl, Pageable pageable);

    @Query("SELECT count(p) FROM Product p WHERE p.category.url=?1")
    int countAllProductByCategory(String categoryUrl);

    Product findProductById(int id);

    @Query ("SELECT p FROM Product p WHERE (p.name like %:query% or p.description like %:query%)" +
            " and (p.producer.id  in (:listProducer)) and (p.category.id in (:listCategory))")
    List<Product> ListProductBySearchForm(@Param("query") String query, @Param("listProducer") List<Integer> prParam, @Param("listCategory") List<Integer> catParam, Pageable pageable);

    @Query ("SELECT count(p) FROM Product p WHERE (p.name like %:query% or p.description like %:query%)" +
            " and (p.producer.id  in (:listProducer)) and (p.category.id in (:listCategory))")
    int countProductBySearchFrom(@Param("query") String query, @Param("listProducer") List<Integer> prParam, @Param("listCategory") List<Integer> catParam);





}
