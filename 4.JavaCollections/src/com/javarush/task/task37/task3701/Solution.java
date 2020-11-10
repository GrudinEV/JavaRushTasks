package com.javarush.task.task37.task3701;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.function.Consumer;

/* 
Круговой итератор
*/

public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }

//        System.out.println("\n---------****---------");
//        Iterator iterator = list.iterator();
//        count = 0;
//        for (int i = 0; i < 10; i++) {
//            System.out.print(iterator.next() + " ");
//            count++;
//            if (count == 6) {
//                iterator.remove();
//            }
//        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator(super.iterator());
    }

    public class RoundIterator implements Iterator<T> {
        private Iterator<T> iter;

        RoundIterator(Iterator<T> iterator) {
            this.iter = iterator;
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public T next() {
            T t = iter.next();
            if (!hasNext()) {
                iter = iterator();
            }
            return t;
        }

        @Override
        public void remove() {
            iter.remove();
            if (!hasNext()) {
                iter = iterator();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            iter.forEachRemaining(action);
        }

//        private void correctionCursorPosition() {
//            Class<?> clazz = iter.getClass();
//            Field fieldCursor = null;
//            try {
//                fieldCursor = clazz.getDeclaredField("cursor");
//            } catch (NoSuchFieldException e) {
//                System.err.println("No such field: cursor!");
//            }
//            if (fieldCursor != null) {
//                fieldCursor.setAccessible(true);
//                int cursor = 0;
//                try {
//                    cursor = fieldCursor.getInt(iter);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                if (cursor == size()) {
//                    try {
//                        fieldCursor.setInt(iter, 0);
//                    } catch (IllegalAccessException e) {
//                        System.out.println("Field cursor not change!");
//                    }
//                }
//            }
//        }
    }
}
