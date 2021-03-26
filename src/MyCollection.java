import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyCollection<E> implements Collection<E> {

    private int size = 0;

    private Object[] elementData = new Object[10];

    public MyCollection(final Integer[] arr) {
    }

    /***/
    @Override
    public boolean add(final E e) {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, (int) (size * 1.5f));
        }
        elementData[size++] = e;
        return true;
    }

    /***/
    @Override
    public int size() {
        return this.size;
    }

    /***/
    @Override
    public boolean isEmpty() {
        return false;
    }

    /***/
    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    /***/
    @Override
    public boolean contains(final Object o) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == null && o == null) || (elementData[i] != null && elementData[i].equals(o))) {
                return true;
            }
        }
        return false;
    }

    /***/
    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        if (size >= 0) {
            System.arraycopy(elementData, 0, objects, 0, size);
        }
        return objects;
    }

    /***/
    @Override
    public <T> T[] toArray(final T[] a) {
        T[] arr = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (T) elementData[i];
        }
        return arr;
    }

    /***/
    @Override
    public boolean remove(final Object o) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == null && o == null) || (elementData[i] != null && elementData[i].equals(o))) {
                return removeAt(i);
            }
        }
        return false;
    }

    private boolean removeAt(final int index) {
        if (size - 1 - index >= 0) {
            System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        }
        size--;
        return true;
    }

    /***/
    @Override
    public boolean containsAll(final Collection<?> c) {
        boolean flag = false;
        Object[] crr = c.toArray();
        for (Object o : crr) {
            flag = false;
            for (int j = 0; j < size; j++) {
                if ((elementData[j] == null && o == null) || (elementData[j] != null && elementData[j].equals(o))) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return flag;
    }

    /***/
    @Override
    public boolean addAll(final Collection<? extends E> c) {
        Object[] crr = c.toArray();
        for (Object o : crr) {
            if (size >= elementData.length) {
                elementData = Arrays.copyOf(elementData, (int) (size * 1.5f));
            }
            elementData[size] = o;
            size++;
        }
        return true;
    }

    /***/
    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean flag = false;
        Object[] crr = c.toArray();

        for (Object o : crr) {
            for (int j = size - 1; j >= 0; j--) {
                if ((elementData[j] == null && o == null) || (elementData[j] != null && elementData[j].equals(o))) {
                    flag = removeAt(j);
                }
            }
        }
        return flag;
    }

    /***/
    @Override
    public boolean retainAll(final Collection<?> c) {
        boolean flag = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elementData[i])) {
                removeAt(i);
                flag = true;
            }
        }
        return flag;
    }

    /***/
    @Override
    public void clear() {
        elementData = new Object[10];
        size = 0;
    }

    private class MyIterator<T, cursor> implements Iterator<T> {

        private int cursor = 0;
        private int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            lastRet = cursor;
            return (T) elementData[cursor++];
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            removeAt(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }
    }
}