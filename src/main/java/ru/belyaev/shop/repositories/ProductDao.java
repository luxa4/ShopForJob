/*
 * Created by Vologda Developer
 * Date: 20.02.2020
 * Time: 15:11
 */

package ru.belyaev.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.belyaev.shop.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {



    @Query(value = "SELECT p, c.name as category, pr.name as producer FROM product p, producer pr, category c "
            + "WHERE c.id=p.id_category and pr.id=p.id_producer offset?1 limit=?2 ", nativeQuery = true)
    List<Product> listAllProduct(int page, int limit);

    @Query(value = "SELECT count(*) FROM product p", nativeQuery = true)
    int countAllProducts();

    Product findProductById(int id);
}
