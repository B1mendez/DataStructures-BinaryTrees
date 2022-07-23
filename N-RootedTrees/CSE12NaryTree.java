/**
 * Name: Brian Mendez
 * ID: A17211975
 * Email: b1mendez@ucsd.edu
 * File description: This program important a number of 
 * data structures and ADT to create a leveled ordered 
 * Nary tree. This tree is designed to hold N children 
 * in each node. 
 */

import java.util.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class extends comparable<E> and is the main class 
 * that initializes the Nary tree data struture. It has one 
 * inner class with two instance variable that initializes 
 * nodes. This class also has three instance variables, those 
 * being the root, size, and N term. 
 */
public class CSE12NaryTree<E extends Comparable<E>> {
    
    /**
     * This inner class encapsulates the data and children for a Node.
     * Do NOT edit this inner class.
     */
    protected class Node{
        E data;
        List<Node> children;
    
        /**
         * Initializes the node with the data passed in
         * 
         * @param data The data to initialize the node with
         */
        public Node(E data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    
        /**
         * Getter for data
         * 
         * @return Return a reference to data
         */
        public E getData() {
            return data;
        }

        /**
         * Setter for the data
         * 
         * @param data Data that this node is set to
         */
        public void setData(E data) {
            this.data = data;
        }

        /**
         * Getter for children
         * 
         * @return reference to the list of children
         */
        public List<Node> getChildren() {
            return children;
        }

        /**
         * Returns the number of children
         * 
         * @return number of children
         */
        public int getNumChildren() {
            // assume there are no nulls in list
            return children.size();
        }

        /**
         * Add the given node to this node's list of children
         * 
         * @param node The node to add
         */
        public void addChild(Node node) {
            children.add(node);
        }
    
    }
    
    Node root;
    int size;
    int N;

    /**
     * Constructor that initializes an empty N-ary tree, with the given N
     * 
     * @param N The N the N-tree should be initialized with
     */
    public CSE12NaryTree(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.root = null;
        this.size = 0;
        this.N = N;
    }

    /**
     * Helper Function that stores the nodes in a List as an 
     * arrayList. The list is stored in level by level order 
     * 
     * @return allNodes - which is a List of the nodes in 
     * level order 
     */
    public List<Node> levelOrder(){
        List<Node> allNodes = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();

        if (root == null){
            return allNodes; 
        }

        q.add(root); 

        while (!q.isEmpty()){ 
            List<Node> temp = new ArrayList<>(); 
            for (int i = 0; i < q.size(); i++){
                Node curr = q.poll();
                temp.add(curr); 

                for (Node currChildren: curr.getChildren()){
                    q.add(currChildren); 
                }
            }

            for (Node data: temp){
                allNodes.add(data); 
            }
        }

        return allNodes; 
    }

    /**
     * Add the following element into the N-ary tree and 
     * set the element as a child to the specific node 
     * required depending on the N value. 
     * 
     * @param element - data being added into the n-ary 
     * tree as a new Node. 
     */
    public void add(E element) {
        if (element == null){
            throw new NullPointerException(); 
        }

        List<Node> levelOrder = levelOrder(); 

        Node newNode = new Node(element);
        for (int i = 0; i < levelOrder.size(); i++){
            Node curr = levelOrder.get(i); 
            if (curr.getNumChildren() < N){
                curr.addChild(newNode);
            }
        }
        size++;
    }

    /**
     * Check if N-ary tree contains the the following 
     * element
     * 
     * @param element - data being searched for in the 
     * n-ary tree as a new Node. 
     * @return true if the element in stored in the node
     * as data in the n-ary tree. 
     */
    public boolean contains(E element) {
        if (element == null){
            throw new NullPointerException(); 
        }

        List<E> data = new ArrayList<>();      
        for (Node temp: levelOrder()){
            data.add(temp.getData()); 
        }
        
        for (int i = 0; i < data.size(); i++){
            if (data.contains(element)){
                return true;
            }
        }

        return false; 
    }

    /**
     * Using a priorityQueue sort the elements in the nary 
     * tree using a heap sort in ascending order. This method 
     * should return an arraylist with elements strictly ascending
     * in value. 
     * 
     * @return arraylist with elements sorted in ascending order.
     */
    public ArrayList<E> sortTree(){
        PriorityQueue<E> minHeap = new PriorityQueue<>();
        ArrayList<E> results = new ArrayList<>();  
        for (Node data: levelOrder()){
            minHeap.add(data.getData()); 
        }
        
        if (size == 0){
            return results; 
        }
        for (int i = 0; i < size; i++){
            results.add(minHeap.poll()); 
        }

        return results;
    }
}
