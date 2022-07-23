/**
 * Name: Brian Mendez
 * ID: A17211975
 * Email: b1mendez@ucsd.edu
 * Sources used: Zybooks and Professor Cao Lecture videos
 * 
 * This file represents an iterator for the binary search tree 
 * and implements the abstract class from MyBSTNodeIterator<T> along 
 * with implementing Iterator<T>. This file also imports the iterator 
 * in java utility and NoSuchElementException. 
 * 
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Main class that iterates through the binary tree. It initializes 
 * two instance variables, one being the next node iterating to and 
 * the other being the lastVisited node that stores the previously 
 * visited node. 
 */
public class MyBSTIterator<K extends Comparable<K>, V> extends MyBST<K, V> {
    abstract class MyBSTNodeIterator<T> implements Iterator<T> {
        MyBSTNode<K, V> next;
        MyBSTNode<K, V> lastVisited;

        /**
         * Constructor that initializes the node iterator
         *
         * @param first The initial node that next points
         */
        MyBSTNodeIterator(MyBSTNode<K, V> first) {
            next = first;
            lastVisited = null;
        }

        /**
         * This method is used for determining if the next pointer in the
         * iterator points to null.
         *
         * @return If next is null based on the current position of iterator
         */
        public boolean hasNext() {
            return next != null;
        }

        MyBSTNode<K, V> nextNode() {
            if (hasNext() == false){
                throw new NoSuchElementException(); 
            }
            else {
                lastVisited = next; 
                next = next.successor();
            } 

            return lastVisited;
        }

        /**
         * Remove the last visited node in the tree stored in the 
         * lastVisited instance variable. After removing balance 
         * out tree if necessary. 
         *
         * This method removes the last visited node from the tree
         */
        public void remove() {
            if (lastVisited == null) {
                throw new IllegalStateException();
            }
            if (lastVisited.getRight() != null &&
                    lastVisited.getLeft() != null) {
                next = lastVisited;
            }
            MyBSTIterator.this.remove(lastVisited.getKey());
            lastVisited = null;
        }
    }

    /**
     * BST Key iterator class that extends the node iterator.
     */
    class MyBSTKeyIterator extends MyBSTNodeIterator<K> {

        MyBSTKeyIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node key
         *
         * @return K the next key
         */
        public K next() {
            return super.nextNode().getKey();
        }
    }

    /**
     * BST value iterator class that extends the node iterator.
     */
    class MyBSTValueIterator extends MyBSTNodeIterator<V> {

        /**
         * Call the constructor method from node iterator
         *
         * @param first The initial value that next points
         */
        MyBSTValueIterator(MyBSTNode<K, V> first) {
            super(first);
        }

        /**
         * This method advance the iterator and returns a node value
         *
         * @return V the next value
         */
        public V next() {
            return super.nextNode().getValue();
        }
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTKeyIterator getKeyIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTKeyIterator(curr);
    }

    /**
     * This method is used to obtain an iterator that iterates through the
     * value of BST.
     *
     * @return The value iterator of BST.
     */
    public MyBSTValueIterator getValueIterator() {
        MyBSTNode<K, V> curr = root;
        if (curr != null) {
            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
        }
        return new MyBSTValueIterator(curr);
    }
}