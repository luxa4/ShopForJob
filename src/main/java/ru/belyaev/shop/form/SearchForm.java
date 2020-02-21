// Created by Vologda developer.
// Date: 26.10.2019
// Time: 12:46

package ru.belyaev.shop.form;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchForm {
    private String query;
    private List<Integer> categories;
    private List<Integer> producers;


    public SearchForm(String query, String[] categories, String[] producers) {
        this.query = query;
        this.categories = convert(categories);
        this.producers = convert(producers);
    }

    private List<Integer> convert (String[] args) {
        if (args == null)
            return Collections.emptyList();
        List<Integer> list = new ArrayList<>(args.length);
        for (String x: args) {
            list.add(Integer.parseInt(x));
        }
        return list;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getProducers() {
        return producers;
    }

    public void setProducers(List<Integer> producers) {
        this.producers = producers;
    }

    public boolean isCategoriesNotEmpty(){
        return !categories.isEmpty();
    }
    public boolean isProducersNotEmpty(){
        return !producers.isEmpty();

    }



}
