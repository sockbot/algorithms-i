/* *****************************************************************************
 *  Name: Andy Chan
 *  Date: 6 Jun 2020
 *  Description: Princeton Algorithms I, Queues
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private Node penultimate;
    private int size = 0;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    private class Node {
        Item item;
        Node next;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (this.isEmpty()) {
            // create first node
            first = new Node();
            first.item = item;
            first.next = null;
            last = first;
        }
        else {
            // create new node
            Node newFirst = new Node();
            newFirst.item = item;
            newFirst.next = first;
            first = newFirst;
        }
        this.size++;
        if (this.size == 2) {
            last = first.next;
            penultimate = first;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (this.isEmpty()) {
            // create first node
            last = new Node();
            last.item = item;
            last.next = null;
            first = last;
        }
        else {
            // create new node
            Node newLast = new Node();
            newLast.item = item;
            newLast.next = null;
            last.next = newLast;
            penultimate = last;
            last = newLast;
        }
        this.size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        // copy first Node
        Node oldFirst = first;
        // move the first pointer to the new first Node
        first = first.next;
        this.size--;
        if (this.size == 1) {
            penultimate = null;
        }
        // if this is the last Node in the list, null the last pointer too
        if (this.isEmpty()) {
            last = null;
        }
        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        this.size--;
        return (Item) this.first;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return this.current.next != null;
        }

        public Item next() {
            if (!this.hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        System.out.println(d.isEmpty());
        d.addFirst(1);
        System.out.println(!d.isEmpty());
        System.out.println(d.first.item == 1);
        System.out.println(d.first.next == null);
        System.out.println(d.last.item == 1);
        System.out.println(d.last.next == null);
        d.addFirst(123);
        System.out.println(!d.isEmpty());
        System.out.println(d.first.item == 123);
        System.out.println(d.first.next);
        System.out.println(d.last.item == 1);
        System.out.println(d.last.next == null);
        d.addLast(234);
        System.out.println(d.first.item == 123);
        System.out.println(d.first.next.next == d.last);
        System.out.println(d.last.item == 234);
        System.out.println(d.last.next == null);
        System.out.println(d.removeFirst() == 123);
        System.out.println(d.removeFirst() == 1);
        System.out.println(d.removeFirst() == 234);
        System.out.println(d.first == null);
        System.out.println(d.last == null);
    }
}
