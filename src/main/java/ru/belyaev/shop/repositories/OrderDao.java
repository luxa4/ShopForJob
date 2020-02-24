/*
 * Created by Vologda Developer
 * Date: 20.02.2020
 * Time: 15:10
 */

package ru.belyaev.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.belyaev.shop.entity.Order;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.model.CurrentAccount;
import ru.belyaev.shop.model.ShoppingCart;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {



    Order findOrderById(Long id);

    int countOrderByAccount_Id(int id);

    @Query(value = "SELECT * FROM \"order\" WHERE id_account=?1 ORDER BY id DESC offset ?2 limit ?3 ", nativeQuery = true)
    List<Order> listMyOrders(int id_account, int page, int limit);


}
