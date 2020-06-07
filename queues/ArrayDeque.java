/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 18 Apr 2020
 *  Description: Princeton Algorithms I, Queues
 **************************************************************************** */

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<Item> implements Iterable<Item> {

    private Item[] deque;
    private int head = 0;
    private int tail = 1;
    private int size = 0;

    // construct an empty deque using arrays
    public ArrayDeque() {
        deque = (Item[]) new Object[1];
    }

    // unit testing (required)
    public static void main(String[] args) {
        ArrayDeque<Integer> d = new ArrayDeque<Integer>();
        d.addLast(100);
        d.addLast(200);
        d.addLast(300);
        d.removeLast();
        d.removeLast();
        d.removeLast();
        d.addFirst(600);
        d.addFirst(500);
        d.addFirst(400);
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.addLast(100);
        d.addLast(200);
        d.addLast(300);
        d.addFirst(600);
        d.removeLast();
        d.addFirst(500);
        d.addLast(100);
        d.removeLast();
        d.addLast(200);
        d.addFirst(600);
        d.removeLast();
        d.addFirst(500);
        d.removeFirst();
        d.removeFirst();
        d.addLast(300);
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.removeLast();
        d.removeFirst();

        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        deque.printStatus();
        deque.addFirst(1);
        deque.printStatus();
        deque.removeFirst();
        deque.printStatus();
        deque.addFirst(3);
        deque.printStatus();
    }

    private void printStatus() {
        System.out.printf("Deque is empty: %b\n", isEmpty());
        System.out.println(Arrays.toString(deque));
        System.out.println("--------------------------------");
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size() == deque.length)
            resize(2 * deque.length); // double the size of the deque array
        head = (head - 1 + deque.length) % deque.length;
        deque[head] = item;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size() == deque.length)
            resize(2 * deque.length); // double the size of the deque array
        int dequeIndex = tail % deque.length;
        deque[dequeIndex] = item;
        size++;
        if (size() > 1)
            tail++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        size--;
        Item first = deque[head];
        deque[head] = null;
        head = (head + 1) % deque.length;
        if (size() != 0 && size() <= deque.length / 4)
            resize(deque.length / 2);
        return first;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        size--;
        Item last = deque[tail - 1];
        deque[--tail] = null;
        if (size() == deque.length / 4)
            resize(deque.length / 2);
        return last;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private final int m = head;
        private int n = tail;

        public boolean hasNext() {
            return n > m;
        }

        public Item next() {
            if (n-- < m)
                throw new NoSuchElementException();
            return deque[--n];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        // copy size() if shrinking capacity, copy deque.length if growing capacity
        int length = Math.min(size(), deque.length);
        for (int copyIndex = 0; copyIndex < length; copyIndex++) {
            int dequeIndex;
            if (head < tail)
                // if head is below tail, just count up normally
                dequeIndex = copyIndex + head;
            else
                // head and tail are reversed, so use modulo to wrap dequeIndex around to beginning
                dequeIndex = (copyIndex + head) % length;
            copy[copyIndex] = deque[dequeIndex];
        }
        deque = copy;
        head = 0;
        tail = size();
    }
}
