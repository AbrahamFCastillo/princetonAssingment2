/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int tail;
    private int tam;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[1];
        tail = 0;
        tam = 0;
    }

    // Metodo de prueba
    private int tamArray() {
        return s.length;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        if (tam == 0)
            return true;
        return false;
    }

    // return the number of items on the randomized queue
    public int size() {
        return tam;
    }

    // add the item
    public void enqueue(Item item) {
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

    private void resizePostDeq(int index) {
        for (int i = index; i < (tail - 1); i++)
            s[i] = s[i + 1];
        s[--tail] = null;
    }

    // remove and return a random item
    public Item dequeue() {
        if (tam <= 0) {
            throw new java.util.NoSuchElementException("Menor a cero");
        }
        int randomIndex = StdRandom.uniform(tail);
        if (s[randomIndex] == null)
            throw new IllegalArgumentException("Vacio");
        Item item = s[randomIndex];
        s[randomIndex] = null;
        resizePostDeq(randomIndex);
        tam--;
        if ((tam > 0) && (tam == s.length / 4)) {
            resize(s.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (tam <= 0) {
            throw new java.util.NoSuchElementException("Menor a cero");
        }
        int randomIndex = StdRandom.uniform(tail);
        if (s[randomIndex] == null)
            throw new IllegalArgumentException("No existe.");
        Item item = s[randomIndex];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return i < tam;
        }

        public void remove() { /* not supported */
            throw new UnsupportedOperationException("not supported");
        }

        public Item next() {
            if (i < tam) {
                return s[i++];
            }
            else {
                throw new java.util.NoSuchElementException("Menor a cero");
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> cola = new RandomizedQueue<String>();
        for (int i = 0; i < 4; i++) {
            String line = StdIn.readString();
            cola.enqueue(line);
            StdOut.println(line);
        }
        StdOut.println(cola.isEmpty());
        StdOut.println("Tamaño de la cola: " + cola.tam);
        StdOut.println("Tamaño del arreglo: " + cola.tamArray());
        StdOut.println(cola.dequeue());
        StdOut.println("Tamaño de la cola: " + cola.tam);
        StdOut.println("Tamaño del arreglo: " + cola.tamArray());
        StdOut.println(cola.dequeue());
        StdOut.println("Tamaño de la cola: " + cola.tam);
        StdOut.println("Tamaño del arreglo: " + cola.tamArray());
        //  StdOut.println(cola.sample());
        StdOut.println(cola.dequeue());
        StdOut.println("Tamaño de la cola: " + cola.tam);
        StdOut.println("Tamaño del arreglo: " + cola.tamArray());
        cola.enqueue("e");
        StdOut.println("Tamaño de la cola: " + cola.tam);
        StdOut.println("Tamaño del arreglo: " + cola.tamArray());
        cola.enqueue("f");
        StdOut.println("Tamaño de la cola: " + cola.tam);
        StdOut.println("Tamaño del arreglo: " + cola.tamArray());
        StdOut.println(cola.dequeue());
        StdOut.println("Tamaño de la cola: " + cola.tam);
        StdOut.println("Tamaño del arreglo: " + cola.tamArray());
        StdOut.println(cola.dequeue());
        StdOut.println("Tamaño del arreglo: " + cola.tamArray());
        StdOut.println(cola.dequeue());
        StdOut.println(cola.isEmpty());

    }

}

