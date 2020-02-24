// Created by Vologda developer.
// Date: 30.10.2019
// Time: 22:35

package ru.belyaev.shop.jdbc;

import java.util.List;

public class SearchQuery {
    private StringBuilder sql;
    private List<Object> params;

    public SearchQuery(StringBuilder sql, List<Object> params) {
        this.sql = sql;
        this.params = params;
    }

    public StringBuilder getSql() {
        return sql;
    }

    public void setSql(StringBuilder sql) {
        this.sql = sql;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }
}
