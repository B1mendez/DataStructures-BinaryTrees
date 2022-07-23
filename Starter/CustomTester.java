/**
 * Name: Brian Mendez
 * ID: A17211975
 * Email: b1mendez@ucsd.edu
 * Sources used: None
 * 
 * Custom tester file for MyBST, MyBSTnode, MyBSTIterator
 * and MyCalender.  
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Custom Test class that tests the following files
 * 
 */
public class CustomTester {
    
    MyBST<Integer, Integer> completeTree2;
    MyBST<Integer, Integer> emptyTree;

    /**
     * The setup tree will look like
     *           8
     *         /  \
     *       3     10
     *     /  |     \
     *    1   6      14
     *       / \     /
     *      4   7   13
     */
    @Before
    public void setup(){

        MyBST.MyBSTNode<Integer, Integer> root = 
            new MyBST.MyBSTNode(8, 10, null);
        MyBST.MyBSTNode<Integer, Integer> three = 
            new MyBST.MyBSTNode(3, 20, root);
        MyBST.MyBSTNode<Integer, Integer> ten = 
            new MyBST.MyBSTNode(10, 30, root);
        MyBST.MyBSTNode<Integer, Integer> one = 
            new MyBST.MyBSTNode(1, 40, three);
        MyBST.MyBSTNode<Integer, Integer> six = 
            new MyBST.MyBSTNode(6, 50, three);
        MyBST.MyBSTNode<Integer, Integer> four = 
            new MyBST.MyBSTNode(4, 60, six);
        MyBST.MyBSTNode<Integer, Integer> seven = 
            new MyBST.MyBSTNode(7, 70, six);
        MyBST.MyBSTNode<Integer, Integer> fourteen = 
            new MyBST.MyBSTNode(14, 80, ten);
        MyBST.MyBSTNode<Integer, Integer> thirteen = 
            new MyBST.MyBSTNode(13, 60, fourteen);

        this.completeTree2 = new MyBST();
        this.completeTree2.root = root;
        root.setLeft(three);
        root.setRight(ten);
        three.setLeft(one);
        three.setRight(six);
        six.setLeft(four); 
        six.setRight(seven);
        ten.setRight(fourteen);
        fourteen.setLeft(thirteen);
        this.completeTree2.size = 9;

        MyBST.MyBSTNode<Integer, Integer> root2 = 
            new MyBST.MyBSTNode(null, null, null);
        this.emptyTree = new MyBST(); 
        this.emptyTree.root = root2;
        this.emptyTree.size = 0; 
    }
    
    // ====== MyBSTNode class ======

    // Test predecessor() on an element with no predecessor
    @Test
    public void testNodePredecessorNullNode() {
        MyBST.MyBSTNode<Integer, Integer> root = completeTree2.root;
        assertSame(null, root.getLeft().getLeft().predecessor()); 
    }

    // ====== MyBST class ======

    // Test insert method when key is a null pointer exception
    @Test
    public void testInsertNull(){
        try {
            MyBST.MyBSTNode<Integer, Integer> root = completeTree2.root; 
            completeTree2.insert(null, 1);
            //fail
        } catch (NullPointerException e){
            //pass 
        }
    }

    // Test insert method when key equals another key in tree.
    // Replace the value with the matching key. 
    @Test
    public void testInsertReplace(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree2.root;
        assertEquals((Integer)10, completeTree2.insert(8,0));
        completeTree2.insert(10, 0);
        assertEquals((Integer)0, completeTree2.insert(8,0));
        assertEquals(9, completeTree2.size()); 

        completeTree2.insert(10, 100); 
        assertEquals((Integer)14, root.getRight().getRight().getKey());
        assertEquals((Integer)100, root.getRight().getValue());
    }

    // Test insert method when the tree is empty
    @Test
    public void testInsertEmpty(){
        MyBST.MyBSTNode<Integer, Integer> root = emptyTree.root;
        //emptyTree.insert(10, 100);
        emptyTree.insert(1, 10); 
        assertEquals((Integer)1, root.getKey()); 
    }


    // Test search method when key is null overrided test
    @Test
    public void testSearchNull(){
        assertNull(null, completeTree2.search(null));
        assertEquals(null, completeTree2.search(1000));
    }

    // Test remove method when key is null and test remove 
    // when the key does not exist. Return null both cases. 
    @Test
    public void testRemoveNull(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree2.root; 
        assertNull(null, completeTree2.remove(null));
        assertNull(null, completeTree2.remove(100));
    }

    // Test remove method when key is internal node with 
    // two children
    @Test
    public void testRemoveNode(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree2.root; 
        assertEquals((Integer)50, completeTree2.remove(6));
        completeTree2.remove(6); 
        assertEquals((Integer)7, root.getLeft().getRight().getKey()); 
    }

    // Test remove method when key is at the root
    @Test
    public void testRemoveRoot(){
        MyBST.MyBSTNode<Integer, Integer> root = completeTree2.root; 
        completeTree2.remove(8); 
        assertEquals((Integer)10, root.getKey()); 
        assertEquals((Integer)3, root.getLeft().getKey());
    }

    // Test inorder method when empty
    @Test
    public void testInorder(){
        MyBST.MyBSTNode<Integer, Integer> root = emptyTree.root; 
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes 
            = new ArrayList<>();
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> actualRes 
            = emptyTree.inorder();
        for (int i=0; i<expectedRes.size(); i++){
            assertSame(expectedRes.get(i), actualRes.get(i));
        }
    }

    // ====== MyBSTIterator class ======

    // Test the initial state of iterator and the function of 
    // properly move to the next node
    @Test
    public void testIteratorNoNext(){
        try {
            MyBSTIterator<Integer, Integer> iterTree = new MyBSTIterator();
            iterTree.root = emptyTree.root;
            MyBSTIterator<Integer, Integer>.MyBSTValueIterator vi = 
            iterTree.new MyBSTValueIterator(iterTree.root);
            vi.nextNode(); 
        } catch (NoSuchElementException e ){
            //pass
        }
    }



}