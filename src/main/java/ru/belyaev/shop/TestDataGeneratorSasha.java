// Created by Vologda developer.
// Date: 15.10.2019
// Time: 8:38

package ru.belyaev.shop;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;

public class TestDataGeneratorSasha {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0 ; i < 10; i++)  {
            list.add(i + 1);
        }

        list.sort((i1, i2) -> {
            if (i1>i2) return -1;
            else  if (i1<i2) return 1;
            else return 0;
        });

        System.out.println(list);
    }





}

class Person {
    int id;
    String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
