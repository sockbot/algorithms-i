/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 18 Apr 2020
 *  Description: Princeton Algorithms I, Queues
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Item[] deque;
    private int head = 0;
    private int tail = 0;

    // construct an empty deque using arrays
    public Deque() {
        deque = (Item[]) new Object[1];
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        System.out.printf("Deque is empty: %b\n", d.isEmpty());
        System.out.printf("%d items in Deque\n", d.size());
        System.out.printf("Deque length is %d\n", d.length());
        System.out.println("--------------------------------");
        System.out.println("Adding 100 to Deque");
        d.addLast(100);
        System.out.printf("Deque is empty: %b\n", d.isEmpty());
        System.out.printf("%d items in Deque\n", d.size());
        System.out.printf("Deque length is %d\n", d.length());
        System.out.println("--------------------------------");
        System.out.println("Adding 200 to Deque");
        d.addLast(200);
        System.out.printf("Deque is empty: %b\n", d.isEmpty());
        System.out.printf("%d items in Deque\n", d.size());
        System.out.printf("Deque length is %d\n", d.length());
        System.out.println("--------------------------------");
        System.out.println("Adding 300 to Deque");
        d.addLast(300);
        System.out.printf("Deque is empty: %b\n", d.isEmpty());
        System.out.printf("%d items in Deque\n", d.size());
        System.out.printf("Deque length is %d\n", d.length());
    }

    // is the deque empty?
    public boolean isEmpty() {
        for (Item d : deque)
            if (d != null)
                return false;
        return true;
    }

    // Temporary public method for debugging
    public int length() {
        return deque.length;
    }

    // return the number of items on the deque
    public int size() {
        return tail - head;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size() == deque.length)
            resize(2 * deque.length); // double the size of the deque array
        deque[tail++] = item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        // copy size() if shrinking capacity, copy deque.length if growing capacity
        int length = Math.min(size(), deque.length);
        for (int i = 0; i < length; i++) {
            int copyIndex = i + (length / 2); // start indexing with equal offset on both ends
            int dequeIndex = (copyIndex + head)
                    % length; // reindex the head and tail if they've wrapped around the index
            copy[copyIndex] = deque[dequeIndex];
        }
        deque = copy;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        return null;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        // if (size() == deque.length)
        //     resize(2 * deque.length); // double the size of the deque array
        // deque[head--] = item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int m = head;
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
}
