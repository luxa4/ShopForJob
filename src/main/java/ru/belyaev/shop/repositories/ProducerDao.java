/*
 * Created by Vologda Developer
 * Date: 20.02.2020
 * Time: 15:11
 */

package ru.belyaev.shop.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.belyaev.shop.entity.Producer;


import java.util.List;

@Repository
public interface ProducerDao extends JpaRepository<Producer, Integer> {


    List<Producer> findAllByOrderByName();


}
