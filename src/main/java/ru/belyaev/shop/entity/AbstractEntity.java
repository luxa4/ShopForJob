// Created by Vologda developer.
// Date: 16.10.2019
// Time: 11:58

package ru.belyaev.shop.entity;

import javax.persistence.*;
import java.util.Objects;

public class AbstractEntity<T> {


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
