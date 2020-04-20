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
        queue = (Item[]) new Object[1];
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
        if (item == null)
            throw new IllegalArgumentException();
        if (size() == queue.length)
            resize(2 * size());
        queue[nextOpen] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (size() < queue.length / 4)
            resize(size() / 2);
        Item item = queue[StdRandom.uniform(size())];
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return queue[StdRandom.uniform(size())];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> bag = new RandomizedQueue<Integer>();
        System.out.printf("Bag is empty: %b\n", bag.isEmpty());
        System.out.println("--------------------------------");
    }

    private class RandomIterator implements Iterator<Item> {

        private Item[] copy;
        private int copyIndex = nextOpen;

        public RandomIterator() {
            copy = (Item[]) new Object();
            int length = Math.min(size(), queue.length);
            for (int i = 0; i < length; i++)
                copy[i] = queue[i];
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            return copyIndex > 0;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return copy[--copyIndex];
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
