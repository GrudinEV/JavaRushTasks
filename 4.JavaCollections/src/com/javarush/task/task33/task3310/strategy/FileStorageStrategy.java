package com.javarush.task.task33.task3310.strategy;

import java.util.LinkedList;

public class FileStorageStrategy implements StorageStrategy{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final int DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private int size;
    private long maxBucketSize;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public int hash(Long k) {
        k ^= (k >>> 20) ^ (k >>> 12);
        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    private Entry getEntry(Long key) {
        int hash = (key == null) ? 0 : hash(key);
        Entry e = null;
        if (table[indexFor(hash, table.length)] != null) {
            e = table[indexFor(hash, table.length)].getEntry();
//            System.out.println(key + " " + e + " " + e.next);
            Long k;
            while (e != null) {
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
                e = e.next;
//                System.out.println(key + " " + e);
            }
        }
        return null;
    }

    private void resize(int newCapacity) {
        FileBucket[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == 1 << 30) {
            return;
        }

        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    private void transfer(FileBucket[] newTable) {
        FileBucket[] src = table;
        int newCapacity = newTable.length;
        Entry[] intermediateTable = new Entry[newCapacity];
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j].getEntry();
            while (e != null) {
                int i = indexFor(e.hash, newCapacity);
                Entry newEntry = e;
                newEntry.next = intermediateTable[i];
                intermediateTable[i] =  newEntry;
                e = e.next;
            }
            src[j].remove();
        }
        for (int j = 0; j < newCapacity; j++) {
            newTable[j].putEntry(intermediateTable[j]);
        }
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex) {
        System.out.println(key + " " + key + "=" + value + " " + bucketIndex);
        FileBucket oldFileBucket = table[bucketIndex];
        Entry e = null;
        if (oldFileBucket != null) {
            e = oldFileBucket.getEntry();
        }
        System.out.println(key + " " + e + " " + e.next);
        FileBucket newFileBucket = new FileBucket();
        Entry newEntry = new Entry(hash, key, value, e);
        System.out.println(key + " " + newEntry + " " + newEntry.next);

        newFileBucket.putEntry(newEntry);
        table[bucketIndex] = newFileBucket;
        if (oldFileBucket != null) {
            oldFileBucket.remove();
        }
        size++;
        if (newFileBucket.getFileSize() >= bucketSizeLimit) {
            resize(2 * table.length);
        }
    }

    private void createEntry(int hash, Long key, String value, int bucketIndex) {
        int index = indexFor(hash, table.length);
        FileBucket oldFileBucket = table[bucketIndex];
        Entry e = oldFileBucket.getEntry();
        FileBucket newFileBucket = new FileBucket();
        Entry newEntry = new Entry(hash, key, value, e);
        newFileBucket.putEntry(newEntry);
        table[bucketIndex] = newFileBucket;
        oldFileBucket.remove();
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        FileBucket[] fileBuckets = table;
        for (int i = 0; i < fileBuckets.length; i++)
            if (fileBuckets[i] != null) {
                for (Entry e = fileBuckets[i].getEntry(); e != null; e = e.next)
                    if (value.equals(e.value))
                        return true;
            }
        return false;
    }

    @Override
    public void put(Long key, String value) {
//        Entry e;
//        if ((e = getEntry(key)) != null) {
//            int index =indexFor(hash(key), table.length);
//            FileBucket oldFileBucket = table[index];
//            oldFileBucket.remove();
//            FileBucket newFileBucket = new FileBucket();
//            Entry newEntry = e;
//            while (newEntry != null) {
//                if (newEntry.value.equals(value)) {
//                    newEntry.value = value;
//                }
//            }
//            newFileBucket.putEntry(e);
//            table[index] = newFileBucket;
//            return;
//        }
        addEntry(hash(key), key, value, indexFor(hash(key), table.length));
    }

    @Override
    public Long getKey(String value) {
        FileBucket[] fileBuckets = table;
        for (int i = 0; i < fileBuckets.length; i++)
            for (Entry e = fileBuckets[i].getEntry(); e != null; e = e.next)
                if (value.equals(e.value))
                    return e.key;
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry e = getEntry(key);
//        System.out.println(key + " " + e);
//        System.out.println("---------------****---------------");
        if (e != null) {
            return e.value;
        }
        return null;
    }
}
