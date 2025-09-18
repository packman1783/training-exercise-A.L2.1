package org.example;

public class Main {
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("A", 1);
        map.put("B", 2);

        System.out.println(map.get("A")); // 1
        System.out.println(map.put("A", 10)); // 1 (старое значение)
        System.out.println(map.get("A")); // 10
        System.out.println(map.remove("B")); // 2
        System.out.println(map.size()); // 1
    }
}