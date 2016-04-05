package edu.petrov.gojavaonline.module999;

import java.util.TreeMap;

/**
 * Created by anton on 04/04/16.
 */
public class Collectionz {
    private static TreeMap<String, Integer> treeMap = new TreeMap<>();

    public static void runTest() {

        treeMap.put("Petrovich", 109);
        treeMap.put("Vasya", 10);
        treeMap.put("Malyshka", 1);
        treeMap.put("Anya", 17);
        treeMap.put("Misha", 88);
        treeMap.put("Boris", 43);
        treeMap.put("Tuzik", 9);
        treeMap.put("John", 5);

        System.out.println(treeMap);

        System.out.println(treeMap.subMap("Anya", true, "Malyshka", true).toString());
    }
}
