package com.skillissue.roastapp.model;

import java.util.List;

public class ModelTest {
    public static void main(String[] args) {
        Subcategory ds = new Subcategory("Data Structures", 0.2);
        Subcategory algo = new Subcategory("Algorithms", 0.2);
        Subcategory sysDesign = new Subcategory("System Design", 0.2);
        Subcategory debug = new Subcategory("Debugging", 0.2);
        Subcategory api = new Subcategory("API Design", 0.2);

        Role sde = new Role("SDE", List.of(ds, algo, sysDesign, debug, api));

        System.out.println("Role: " + sde.name());
        System.out.println("Subcategories: " + sde.subcategories());
    }
}