package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max(16, (int) Math.ceil(collection.size() / .75f)));
        collection.forEach(e -> add(e));
    }

    @Override
    public boolean add(E e) {
        return map.putIfAbsent(e, PRESENT) == null ? true : false;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {

        return map.remove(o).equals(PRESENT) ? true : false;
    }

    @Override
    public Object clone() throws InternalError {
        AmigoSet<E> cloneSet;
        try {
            cloneSet = (AmigoSet) super.clone();
            cloneSet.map = (HashMap) map.clone();
        } catch (Exception e) {
            throw new InternalError();
        }
        return cloneSet;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(size());
        map.keySet().forEach(eObjectEntry -> {
            try {
                objectOutputStream.writeObject(eObjectEntry);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        objectOutputStream.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        objectOutputStream.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        objectOutputStream.flush();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Set<E> set = new HashSet<>();
        int size = (int) objectInputStream.readObject();
        for (int i = 0; i < size; i++) {
            set.add((E) objectInputStream.readObject());
        }
        int capacity = (int) objectInputStream.readObject();
        float loadFactor = (float) objectInputStream.readObject();
        map = new HashMap<>(capacity, loadFactor);
        set.forEach(e -> map.put(e, PRESENT));
    }

}
