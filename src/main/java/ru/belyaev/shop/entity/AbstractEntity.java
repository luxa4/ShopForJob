// Created by Vologda developer.
// Date: 16.10.2019
// Time: 11:58

package ru.belyaev.shop.entity;

import javax.persistence.Column;
import java.util.Objects;

public abstract class AbstractEntity<T> {
    @Column(name = "id")
    T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id + getClass().getSimpleName() +
                '}';
    }
}
