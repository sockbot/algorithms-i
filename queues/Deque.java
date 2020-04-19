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
    private int tail = 1;
    private int size = 0;

    // construct an empty deque using arrays
    public Deque() {
        deque = (Item[]) new Object[1];
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        d.printStatus();
        System.out.println("addLast 100 to Deque");
        d.addLast(100);
        d.printStatus();
        System.out.println("addLast 200 to Deque");
        d.addLast(200);
        d.printStatus();
        System.out.println("addLast 300 to Deque");
        d.addLast(300);
        d.printStatus();
        System.out.printf("removeLast %d from Deque\n", d.removeLast());
        d.printStatus();
        System.out.printf("removeLast %d from Deque\n", d.removeLast());
        d.printStatus();
        System.out.printf("removeLast %d from Deque\n", d.removeLast());
        d.printStatus();
        System.out.println("addFirst 400 to Deque");
        d.addFirst(400);
        d.printStatus();
        System.out.println("addFirst 500 to Deque");
        d.addFirst(500);
        d.printStatus();
        System.out.println("addFirst 600 to Deque");
        d.addFirst(600);
        d.printStatus();
    }

    private void printStatus() {
        System.out.printf("Deque is empty: %b\n", isEmpty());
        System.out.printf("%d items in Deque, Deque length is %d\n", size(), length());
        System.out.println("--------------------------------");
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
        return null;
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

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        // copy size() if shrinking capacity, copy deque.length if growing capacity
        int length = Math.min(size(), deque.length);
        for (int copyIndex = 0; copyIndex < length; copyIndex++) {
            // use modulo to wrap dequeIndex around to beginning
            int dequeIndex = (copyIndex + head) % length;
            copy[copyIndex] = deque[dequeIndex];
        }
        deque = copy;
        // head = 0;
        // tail = size();
    }
}
