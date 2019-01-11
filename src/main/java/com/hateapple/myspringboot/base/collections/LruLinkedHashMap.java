package com.hateapple.myspringboot.base.collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruLinkedHashMap<K,V> extends LinkedHashMap<K,V>{

    private int cap ;

    LruLinkedHashMap(int cap){
        super(16, 0.75f, true);
        this.cap = cap;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>cap;
    }

    public static void main(String[] args) {
        LruLinkedHashMap<String, String> map = new LruLinkedHashMap<>(3);
        map.put("name", "jack");
        map.put("age", "12");
        map.put("addres", "hz");

        showMap(map);
        map.get("name");
        showMap(map);
        map.put("sex", "man");
        showMap(map);
    }

    public static void  showMap(Map<String, String> map){
        System.out.println();
        for (String key : map.keySet()) {
            System.out.print(key+",");
        }
        System.out.println();
    }
}