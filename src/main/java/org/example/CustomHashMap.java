package org.example;

public class CustomHashMap<K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;
    private int size = 0;
    private int capacity = 13;
    private final double loadFactor = 0.75;

    public CustomHashMap() {
        this.buckets = (Node<K, V>[]) new Node[capacity];
    }

    private int hash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> current = buckets[index];
        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public V put(K key, V value) {
        if ((double) size / capacity >= loadFactor) {
            resize();
        }

        int index = hash(key);
        Node<K, V> current = buckets[index];
        while (current != null) {
            if ((key == null && current.key == null ) || (key != null && key.equals(current.key))) {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            current = current.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;

        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while(current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }

        return null;
    }


    private void resize() {
        capacity = capacity * 2;
        Node<K, V>[] oldBuckets = buckets;
        buckets = (Node<K, V>[]) new Node[capacity];
        size = 0;

        for(Node<K, V> node : oldBuckets) {
            while(node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    public int size() {
        return size;
    }
}
