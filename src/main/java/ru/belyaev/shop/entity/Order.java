// Created by Vologda developer.
// Date: 16.10.2019
// Time: 12:09

package ru.belyaev.shop.entity;




import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="\"order\"")
public class Order  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account")
    private Account account;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> products;

    @Column(name = "created")
    private Timestamp created;

    @Transient
    private int totalCount;


    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalCount(List<OrderItem> products) {
        totalCount = 0;
        if (products != null) {
            for (OrderItem item : products) {
                totalCount += item.getCount();
            }
            return totalCount;
        }

        return totalCount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<OrderItem> getProducts() {
        return products;
    }



    public void setProducts(List<OrderItem> products) {

        this.products = products;

    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public BigDecimal getTotalCost() {
        BigDecimal cost = BigDecimal.ZERO;
        if (products != null) {
            for (OrderItem item : products) {
                cost = cost.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCount())));
            }
        }
        return cost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "account=" + account +
                ", products=" + products +
                ", created=" + created +
                ", totalCount=" + totalCount +
                ", id=" + id +
                '}';
    }
}
