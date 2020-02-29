/*
 * Created by Vologda Developer
 * Date: 20.02.2020
 * Time: 15:10
 */

package ru.belyaev.shop.repositories;

import org.springframework.data.domain.Pageable;
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

    @Query("SELECT ord FROM Order ord WHERE ord.account.id=?1")
    List<Order> listMyOrders(int id_account, Pageable pageable);

}
