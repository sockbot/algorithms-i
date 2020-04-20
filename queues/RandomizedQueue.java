/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 19 Apr 2020
 *  Description: Algorithms Part I, Queues
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int size = 0;
    private int nextOpen = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (size() == queue.length)
            resize(2 * size());
        queue[nextOpen] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size() < queue.length / 4)
            resize(size() / 2);
        Item item = queue[StdRandom.uniform(size())];
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return queue[StdRandom.uniform(size())];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

    private class RandomIterator implements Iterator<Item> {

        private int m = 0;
        private int n = 0;

        public boolean hasNext() {
            return n > m;
        }

        public Item next() {
            if (n-- < m)
                throw new NoSuchElementException();
            return queue[--n];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        // copy size() if shrinking capacity, copy queue.length if growing capacity
        int length = Math.min(size(), queue.length);
        for (int i = 0; i < length; i++)
            copy[i] = queue[i];
        nextOpen = size();
        queue = copy;
    }
}
