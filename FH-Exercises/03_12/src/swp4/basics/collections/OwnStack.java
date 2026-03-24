package swp4.basics.collections;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

public class OwnStack<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public OwnStack() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public OwnStack(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity must be non-negative");
        }
        this.elements = new Object[Math.max(initialCapacity, DEFAULT_CAPACITY)];
        this.size = 0;
    }

    public void push(T item) {
        // Allow null elements; if you prefer to forbid nulls, uncomment the next line
        // Objects.requireNonNull(item, "null elements not allowed");
        ensureCapacity(size + 1);
        elements[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int idx = --size;
        T value = (T) elements[idx];
        elements[idx] = null; // help GC
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public String toString() {
        Object[] active = Arrays.copyOf(elements, size);
        return "OwnStack" + Arrays.toString(active);
    }
}
