/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 18 Apr 2020
 *  Description: Princeton Algorithms I, Queues
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> {

    private Item[] deque;

    // construct an empty deque using arrays
    public Deque() {
        this.deque = (Item[]) new Object[1];
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        d.isEmpty();
    }

    // is the deque empty?
    public boolean isEmpty() {
        System.out.printf("Deque length is %d", this.deque.length);
        return (this.deque.length == 0);
    }

    // return the number of items on the deque
    public int size() {
        return 0;
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
        return null;
    }
}
