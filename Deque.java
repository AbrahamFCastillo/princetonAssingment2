/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Item s[];
    private int tail;
    private int head;
    private int tam;

    // construct an empty deque
    public Deque() {
        s = (Item[]) new Object[1];
        tail = 0;
        head = 0;
        tam = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return tam == 0;
    }

    // return the number of items on the deque
    public int size() {
        return tam;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item != null) {
            if (tail == s.length) {
                resize(2 * s.length);
            }
            recorrerUno();
            s[head] = item;
            tail++;
            tam++;
        }
        else {
            throw new IllegalArgumentException("Es null");
        }
    }

    private void recorrerUno() {
        for (int i = 1; i <= tail; i++) {
            s[i] = s[i - 1];
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item != null) {
            if (tail == s.length) {
                resize(2 * s.length);
            }
            s[tail++] = item;
            tam++;
        }
        else {
            throw new IllegalArgumentException("Es null");
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int i;
        for (i = 0; i < tail; i++)
            if (s[i] != null) {
                copy[i] = s[i];
            }
        s = copy;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (tam <= 0) {
            throw new java.util.NoSuchElementException("Menor a cero");
        }
        Item item = s[head];
        s[head] = null;
        head++;
        tam--;
        if ((tam > 0) && (tam == s.length / 4)) {
            resize(s.length / 2);
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (tam <= 0) {
            throw new java.util.NoSuchElementException("Menor a cero");
        }
        Item item = s[--tail];
        s[tail] = null;
        tam--;
        if ((tam > 0) && (tam == s.length / 4)) {
            resize(s.length / 2);
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
