/*
 * Created by Vologda Developer
 * Date: 20.02.2020
 * Time: 15:11
 */


package ru.belyaev.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belyaev.shop.entity.Order;
import ru.belyaev.shop.entity.OrderItem;
import ru.belyaev.shop.entity.Product;
import ru.belyaev.shop.model.CurrentAccount;

import java.util.List;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItem, Long> {


    List<OrderItem> findOrderItemByOrder_Id(Long id);
}
