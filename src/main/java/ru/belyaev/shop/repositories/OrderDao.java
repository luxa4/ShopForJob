/*
 * Created by Vologda Developer
 * Date: 20.02.2020
 * Time: 15:10
 */

package ru.belyaev.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belyaev.shop.entity.Order;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.model.CurrentAccount;
import ru.belyaev.shop.model.ShoppingCart;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {

    long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount);

    Order findOrOrderById(Long id);

    List<Order> listMyOrders(CurrentAccount currentAccount, int page, int limit);

    int countMyOrders(CurrentAccount currentAccount);
}
