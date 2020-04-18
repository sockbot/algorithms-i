/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 18 Apr 2020
 *  Description: Princeton Algorithms I, Queues
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> {

    private Item[] deque;
    private int numItems = 0; // number of Items in deque

    // construct an empty deque using arrays
    public Deque() {
        this.deque = (Item[]) new Object[1];
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        System.out.printf("Deque is empty: %b\n", d.isEmpty());
        System.out.printf("Deque size is %d\n", d.size());
    }

    // is the deque empty?
    public boolean isEmpty() {
        for (Item d : deque)
            if (d != null)
                return false;
        return true;
    }

    // return the number of items on the deque
    public int size() {
        return numItems;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        return null;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = numItems;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (i-- < 0)
                throw new NoSuchElementException();
            return deque[--i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
