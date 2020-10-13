package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    int countElements = 0;

    public CustomTree() {
        this.root = new Entry<String>("root");
    }

    @Override
    public boolean add(String s) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry.isAvailableToAddChildren()){
                Entry<String> newEntry = new Entry<>(s);
                newEntry.parent = entry;
                if (entry.availableToAddLeftChildren) {
                    entry.leftChild = newEntry;
                    entry.availableToAddLeftChildren = false;
                } else {
                    entry.rightChild = newEntry;
                    entry.availableToAddRightChildren = false;
                }
                countElements++;
                return true;
            } else {
                if (entry.leftChild != null) {
                    queue.add(entry.leftChild);
                }
                if (entry.rightChild != null) {
                    queue.add(entry.rightChild);
                }
            }
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry.leftChild == null) {
                entry.availableToAddLeftChildren = true;
            } else {
                queue.add(entry.leftChild);
            }
            if (entry.rightChild == null) {
                entry.availableToAddRightChildren = true;
            } else {
                queue.add(entry.rightChild);
            }
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry.isAvailableToAddChildren()){
                Entry<String> newEntry = new Entry<>(s);
                newEntry.parent = entry;
                if (entry.availableToAddLeftChildren) {
                    entry.leftChild = newEntry;
                    entry.availableToAddLeftChildren = false;
                } else {
                    entry.rightChild = newEntry;
                    entry.availableToAddRightChildren = false;
                }
                countElements++;
                return true;
            } else {
                if (entry.leftChild != null) {
                    queue.add(entry.leftChild);
                }
                if (entry.rightChild != null) {
                    queue.add(entry.rightChild);
                }
            }
        }
        return false;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return countElements;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    String getParent(String s) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry.leftChild != null) {
                if (entry.leftChild.elementName.equals(s)) {
                    return entry.elementName;
                }
            }
            if (entry.rightChild != null && entry.rightChild.elementName.equals(s)){
                return entry.elementName;
            } else {
                if (entry.leftChild != null) {
                    queue.add(entry.leftChild);
                }
                if (entry.rightChild != null) {
                    queue.add(entry.rightChild);
                }
            }
        }
        return null;
    }

    public boolean remove(Object o) {
        if (!(o instanceof String)) {
            throw new UnsupportedOperationException();
        }
        String s = (String) o;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (entry.elementName.equals(s)) {
                Queue<Entry<String>> deletedElements = new LinkedList<>();
                deletedElements.add(entry);
                int countDeletedElements = 0;
                while (!deletedElements.isEmpty()) {
                    Entry<String> deletedEntry = deletedElements.poll();
                    countDeletedElements++;
                    if (!deletedEntry.availableToAddLeftChildren && deletedEntry.leftChild != null) {
                        deletedElements.add(deletedEntry.leftChild);
                    }
                    if (!deletedEntry.availableToAddRightChildren && deletedEntry.rightChild != null) {
                        deletedElements.add(deletedEntry.rightChild);
                    }
                }
                countElements -= countDeletedElements;
                if (entry.parent.leftChild != null && entry.parent.leftChild.elementName.equals(s)) {
                    entry.parent.leftChild = null;
                } else  {
                    entry.parent.rightChild = null;
                }
                return true;
            } else {
                if (entry.leftChild != null) {
                    queue.add(entry.leftChild);
                }
                if (entry.rightChild != null) {
                    queue.add(entry.rightChild);
                }
            }
        }
        return false;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }
        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
