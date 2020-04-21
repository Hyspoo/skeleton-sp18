public class ArrayDeque<T> {

    private T[] items;
    private int firstIndex;
    private int size;

    public ArrayDeque() {
        items = new T[256];
        firstIndex = 128;
        size = 0;
    }

    private void resize(int begin, int capacity) {
        T[] a = new T[capacity];
        System.arraycopy(items, firstIndex, a, begin, size);
        items = a;
    }

    public void addFirst(T item) {
        if (firstIndex == 0) {
            int len = items.length;
            items.resize(len + firstIndex, 2 * len);
            firstIndex = items.length;
        }
        items[firstIndex - 1] = item;
        firstIndex -= 1;
        size += 1;
    }

    public void addLast(T item) {
        if (firstIndex + size == items.length) {
            items.resize(firstIndex, 2 * items.length);
        }
        items[firstIndex + size] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int pos = firstIndex;
        while (pos < firstIndex + size) {
            System.out.print(items[pos]);
            System.out.print(" ");
            pos += 1;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[firstIndex];
        items[firstIndex] = null;
        firstIndex += 1;
        size -= 1;
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = items[firstIndex + size - 1];
        items[firstIndex + size - 1] = null;
        size -= 1;
        return item;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null
        }
        return items[firstIndex + index];
    }

}
