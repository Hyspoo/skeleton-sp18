public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int firstIndex;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        firstIndex = 4;
        size = 0;
    }

    private void resize(int begin, int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, firstIndex, a, begin, size);
        items = a;
    }

    @Override
    public void addFirst(T item) {
        if (firstIndex == 0) {
            resize(items.length, items.length * 2);
            firstIndex = items.length / 2;
        }
        if (size != 0) {
            firstIndex -= 1;
        }
        items[firstIndex] = item;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (firstIndex + size == items.length) {
            resize(firstIndex, items.length * 2);
        }
        items[firstIndex + size] = item;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int pos = firstIndex;
        while (pos < firstIndex + size) {
            System.out.print(items[pos]);
            System.out.print(" ");
            pos += 1;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[firstIndex];
        items[firstIndex] = null;
        firstIndex += 1;
        size -= 1;
        if (size < items.length / 4 && items.length > 8) {
            resize(items.length / 8, items.length / 2);
            firstIndex = items.length / 4;
        }
        if (size == 0) {
            firstIndex = items.length / 2;
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = items[firstIndex + size - 1];
        items[firstIndex + size - 1] = null;
        size -= 1;
        if (size < items.length / 4 && items.length > 8) {
            resize(items.length / 8, items.length / 2);
            firstIndex = items.length / 4;
        }
        if (size == 0) {
            firstIndex = items.length / 2;
        }
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[firstIndex + index];
    }

}
