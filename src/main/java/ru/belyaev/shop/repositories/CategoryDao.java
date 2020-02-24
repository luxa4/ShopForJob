/*
 * Created by Vologda Developer
 * Date: 20.02.2020
 * Time: 15:10
 */

package ru.belyaev.shop.repositories;

import com.sun.prism.shader.Solid_RadialGradient_REFLECT_AlphaTest_Loader;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.belyaev.shop.entity.Category;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {


    List<Category> findAllByOrderByName();
}
