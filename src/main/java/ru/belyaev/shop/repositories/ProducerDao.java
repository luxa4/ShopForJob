/*
 * Created by Vologda Developer
 * Date: 20.02.2020
 * Time: 15:11
 */

package ru.belyaev.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belyaev.shop.entity.Producer;
import ru.belyaev.shop.entity.Product;

import java.util.List;

@Repository
public interface ProducerDao extends JpaRepository<Producer, Integer> {

    // return list of all Producers - edit sql query - Нужно для отображения производителей в списке поиска блока ASIDE
    List<Producer> listAllProducers();
}
