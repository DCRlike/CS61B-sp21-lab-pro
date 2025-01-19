package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    // Node
    private class Node {
        Node next;
        Node prev;
        T data;
        Node(Node prev, T data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    // Iterator
    private class LinkedListDequeIterator implements Iterator<T> {
        private Node iterNode;

        LinkedListDequeIterator() {
            iterNode = sentinel.next;
        }

        public boolean hasNext() {
            return iterNode != sentinel;
        }

        public T next() {
            T returnItem = iterNode.data;
            iterNode = iterNode.next;
            return returnItem;
        }
    }
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }


    private Node sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
/*  io operation
*   TODO: doc of 'add' and 'remove'
*/
    @Override
    public void addFirst(T item) {
        Node temp = new Node(null, item, null);
        size++;
        sentinel.next.prev = temp;
        sentinel.next=temp;
    }

    @Override
    public void addLast(T item) {
        Node temp = new Node(null, item, null);
        sentinel.prev.next = temp;
        sentinel.prev=temp;
        size += 1;
    }

    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        Node prevFirstNode = sentinel.next;
        if (prevFirstNode == sentinel) {
            return null;
        }

        Node newFirstNode = prevFirstNode.next;
        T item = prevFirstNode.data;
        sentinel.next = newFirstNode;
        newFirstNode.prev = sentinel;
        size --;
        return item;
    }

    @Override
    public T removeLast() {
        Node prevlastnode = sentinel.prev;
        if (prevlastnode == sentinel) {
            return null;
        }


        T item = prevlastnode.data;
        sentinel.prev = prevlastnode.prev;
        prevlastnode.prev.next = sentinel;
        size --;
        return item;
    }

    @Override
    public T get(int index) {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (index == 0) {
                return p.data;
            }
            index -= 1;
            p = p.next;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            T item1 = get(i);
            T item2 = other.get(i);
            if (!item1.equals(item2)) {
                return false;
            }
        }
        return true;
    }
}
