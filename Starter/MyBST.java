/**
 * Name: Brian Mendez
 * ID: A17211975
 * Email: b1mendez@ucsd.edu
 * Sources used: Zybooks and Professor Cao Lecture videos
 * 
 * This file represents a binary search tree where sorting 
 * is done based on the keys and values. It implements two 
 * class that represent the binary search tree and contains 
 * methods that balance out trees when called for and 
 * initialized. This file also imports the arraylist utilities.
 * 
 */
import java.util.ArrayList;

/**
 * This class represent a binary search tree that implements
 * methods specified for a binary search tree. It extends to 
 * a Comparable function. It has two instance variables, one 
 * that implements the root of the tree and how many elements 
 * are in three.  
 */
public class MyBST<K extends Comparable<K>,V>{
    MyBSTNode<K,V> root = null;
    int size = 0;

    /**
     * Get how many elements are in the tree
     * @return size of tree
     */
    public int size(){
        return size;
    }

    /**
     * Insert the given key and value into the binary search tree
     * and iterate it into the correct position. If if key matches
     * with another node in tree replace it and return the value 
     * being replaced. 
     * 
     * @param key - where the node should be implemented in
     * @param value - the value of the element 
     * @return the value being replaced by the node being 
     * implemented 
     */
    public V insert(K key, V value){
        if (key == null){
            throw new NullPointerException(); 
        }

        MyBSTNode<K,V> newNode = new MyBSTNode<K,V>(key, value, null); 
        MyBSTNode<K,V> curr = root;
        V replaced;

        if (curr.getValue() == null && curr.getKey() == null){
            curr.setKey(newNode.getKey()); 
            curr.setValue(newNode.getValue());
            size++;  
            return null;
        }
        if (key.compareTo(root.getKey()) == 0){
            replaced = root.getValue(); 
            root.setValue(value);
            return replaced; 
        }
 
        while (curr != null){
            if (key.compareTo(curr.getKey()) == 0){
                replaced = curr.getValue(); 
                curr.setValue(value);
                return replaced; 
            }

            if (key.compareTo(root.getKey()) == -1){
                if (curr.getLeft() == null){
                    curr.setLeft(newNode);
                    newNode.setParent(curr);
                    size++; 
                    return null; 
                }
                else {
                    curr = curr.getLeft(); 
                } 
            }
            else {
                if (curr.getRight() == null){
                    curr.setRight(newNode);
                    newNode.setParent(curr);
                    size++;
                    return null;
                }
                else {
                    curr = curr.getRight(); 
                }
            }
        }
        return null;
    }

    /**
     * Search for a specific key in the binary tree and 
     * return the value associated with the key. 
     * 
     * @param key - the node the method is searching for 
     * in the tree. 
     * @return - the value of node being searched 
     */
    public V search(K key){
        if (key == null){
            return null;
        }

        MyBSTNode<K,V> curr = root;
        while (curr != null){
            if (key.compareTo(curr.getKey()) == 0){
                return curr.getValue();
            }
            else if (key.compareTo(curr.getKey()) == -1){
                curr = curr.getLeft(); 
            }
            else {
                curr = curr.getRight(); 
            }
        }
        return null;
    }

    /**
     * Remove the element from the tree with the matching key and 
     * return the value that was stored inside in the node. After 
     * removing the node, the method will follow a binary search 
     * tree and its rules. 
     * 
     * @param key - key of node being removed
     * @return - removed value from the bst 
     */
    public V remove(K key){
        if (key == null){
            return null;
        }

        if (search(key) == null){
            return null;
        }

        MyBSTNode<K,V> curr = root;
        MyBSTNode<K,V> successorNode; 
        V removedNode; 
        while (curr != null){
            if (key.compareTo(curr.getKey()) == 0){
                break;
            }
            else if (key.compareTo(curr.getKey()) == -1){
                curr = curr.getLeft(); 
            }
            else {
                curr = curr.getRight(); 
            }
        }

        if (curr.getParent() == null){
            removedNode = curr.getValue(); 
            successorNode = curr.successor(); 
            curr.setKey(successorNode.getKey());
            curr.setValue(successorNode.getValue());
        }
        else if (curr.getLeft() != null && curr.getRight() != null){
            removedNode = curr.getValue(); 
            successorNode = curr.successor();
            curr = successorNode;  
            if (successorNode.getParent().getLeft() != null){
                successorNode.getParent().setLeft(null); 
            }
            else {
                successorNode.getParent().setRight(null);
            }

            return removedNode; 
        }
        else if (curr.getLeft() != null && curr.getRight() == null){
            removedNode = curr.getValue(); 
            curr.setKey(curr.getLeft().getKey());
            curr.setValue(curr.getLeft().getValue());
            curr.setLeft(null); 
            return removedNode; 
        }
        else if (curr.getRight() != null && curr.getLeft() == null) {
            removedNode = curr.getValue(); 
            curr.setKey(curr.getRight().getKey());
            curr.setValue(curr.getRight().getValue());
            curr.setRight(null);
            return removedNode; 
        }
        else {
            removedNode = curr.getValue(); 
            curr = curr.getParent(); 

            if (curr.getRight() != null){
                curr.setRight(null);
            }
            else {
                curr.setLeft(null);
            }
            return removedNode; 
        }

        size--; 
        return null;
    }
    
    /**
     * Store elements inorder form a bst into an 
     * arraylist. 
     * 
     * @return the arraylist with the elements in 
     * the bst
     */
    public ArrayList<MyBSTNode<K, V>> inorder(){
        ArrayList<MyBSTNode<K, V>> inorder = new ArrayList<>(size); 
        MyBSTNode<K,V> curr = root;

        while (curr.getLeft() != null){
            curr = curr.getLeft(); 
        }

        while(curr != null){
            inorder.add(curr); 
            curr = curr.successor(); 
        }

        return inorder;
    }

    /**
     * This class implements rules that make up a
     * binary search tree. It has five instance 
     * variables three being BSTNodes (parent, left 
     * and right) and a key and value variable that 
     * represents the location of the binary search 
     * tree. 
     */
    static class MyBSTNode<K,V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K,V> parent;
        private MyBSTNode<K,V> left = null;
        private MyBSTNode<K,V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         * @param key the key the MyBSTNode<K,V> will
         * @param value the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent; 
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey(){
            return key;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue(){
            return value;
        }

        /**
         * Return the parent
         * @return the parent
         */
        public MyBSTNode<K,V> getParent(){
            return parent;
        }

        /**
         * Return the left child 
         * @return left child
         */
        public MyBSTNode<K,V> getLeft(){
            return left;
        }

        /**
         * Return the right child 
         * @return right child
         */
        public MyBSTNode<K,V> getRight(){
            return right;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         * @param newKey the key to be stored
         */
        public void setKey(K newKey){
            this.key = newKey;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         * @param newValue the data to be stored
         */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
         * Set the parent
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K,V> newParent){
            this.parent = newParent;
        }

        /**
         * Set the left child
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K,V> newLeft){
            this.left = newLeft;
        }

        /**
         * Set the right child
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K,V> newRight){
            this.right = newRight;
        }

        /**
         * The successor of a Node returns the smallest key greater than the 
         * initial Node. To find this, there are three specific cases we are 
         * going to test: 1. if the Node has a right child, get the leftchild 
         * till null. 2. if the node is the right child of a parent, traverse 
         * up the tree until the node is not a right child of a node. 3. if 
         * the node is the left child of a parent return the parent. 
         *
         * This method returns the in order successor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the successor of current node object
         */
        public MyBSTNode<K, V> successor(){
            if(this.getRight() != null){
                MyBSTNode<K,V> curr = this.getRight();
                while(curr.getLeft() != null){
                    curr = curr.getLeft();
                }
                return curr;
            }
            else{
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                while(parent != null && curr == parent.getRight()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /**
         * The predecessor of a Node returns the greatest key smaller than the 
         * initial Node. To find this, there are three specific cases we are 
         * going to test: 1. if the Node has a left child, get the rightchild 
         * till null. 2. if the node is the left child of a parent, traverse 
         * up the tree until the node is not a left child of a node. 3. if 
         * the node is the right child of a parent return the parent. 
         *
         * This method returns the in order predecessor of current node object.
         * It can be served as a helper method when implementing inorder().
         * @return the predecessor of current node object
         */
        public MyBSTNode<K, V> predecessor(){
            if(this.getLeft() != null){
                MyBSTNode<K,V> curr = this.getLeft();
                while(curr.getRight() != null){
                    curr = curr.getRight();
                }
                return curr;
            }
            else{
                MyBSTNode<K,V> parent = this.getParent();
                MyBSTNode<K,V> curr = this;
                while(parent != null && curr == parent.getLeft()){
                    curr = parent;
                    parent = parent.getParent();
                }
                return parent;
            }
        }

        /** This method compares if two node objects are equal.
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj){
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K,V> comp = (MyBSTNode<K,V>)obj;
            
            return( (this.getKey() == null ? comp.getKey() == null : 
                this.getKey().equals(comp.getKey())) 
                && (this.getValue() == null ? comp.getValue() == null : 
                this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         * @return "Key:Value" that represents the node object
         */
        public String toString(){
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}