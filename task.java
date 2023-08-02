import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.RowFilter.Entry;

public class MyHashMap<K, V> implements Iterable<MyHashMap.Entry<K, V>> {

    private Entry<K, V>[] table;
    private int capacity;
    private int size;

    public MyHashMap(int capacity){
        this.capacity = capacity;
        table = new Entry[capacity];
    }
    // остальные методы класса
    @Override
    public Iterator<Entry<K, V>> iterator(){
        return new MyHashMapIterator();

    }
    private class MyHashMapIterator implements Iterator<Entry<K, V>>{
        private int index;
        private Entry<K, V> currentEntry;
        private Entry<K, V> lastReturned;

        public MyHashMapIterator(){
            index = 0;
            currentEntry = null;
            lastReturned = null;
        }

        @Override
        public boolean hasNext(){
            if(currentEntry != null && currentEntry.next != null){
                return true;
            }
            while(index < capacity){
                if(table[index] != null){
                    return true;
                }
                index++;
            }
            return false;
        }

        @Override

        public Entry<K, V> next(){
            if(!hasNext()){
                throw new NoSuchElementException("No more elements in MyHashMap");
            }
            if(currentEntry == null || currentEntry.next == null){
                while(table[index] == null){
                    index++;
                }
                currentEntry = table[index];                
            }
            else{
                currentEntry = currentEntry.next;
            }
            lastReturned = currentEntry;
            return currentEntry;
        }
        @Override

        public void remove(){
            if(lastReturned == null){
                throw new IllegalStateException("next() has not been called");
                
            }
            MyHashMap.this.remove(lastReturned.key);
            lastReturned = null;
        }
        
    }
    public static class Entry<K, V>{
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value, Entry<K,V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
} 
