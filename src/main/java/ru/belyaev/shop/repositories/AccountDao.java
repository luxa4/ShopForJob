/*
 * Created by Vologda Developer
 * Date: 20.02.2020
 * Time: 15:10
 */

package ru.belyaev.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belyaev.shop.entity.Account;
import ru.belyaev.shop.entity.Product;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {

    Account findAccountByEmail(String email);
}
