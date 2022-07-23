/**
 * Name: Brian Mendez
 * ID: A17211975
 * Email: b1mendez@ucsd.edu
 * File description: This is a public tester file that tests
 * the methods created in the CSE12NaryTree file.
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * Main Class to test public tester files. This class specifically
 * runs tests for CSE12NaryTree file. 
 */
public class CSE12NaryTreeTester {
    private CSE12NaryTree<Integer> tree;
    private Integer[] intData = {4, 3, 6, 1, 2, 5};  

    /**
     * Initializing the constructor for CSE12NaryTree
     */
    @Before
    public void setup(){
        tree = new CSE12NaryTree<>(5);
    }

    /**
     * Creating an 5ary tree that is filled up until level 1
     */
    private void NaryTree(){
        CSE12NaryTree<Integer>.Node root = 
            this.tree.new Node(intData[0]);
        CSE12NaryTree<Integer>.Node child1 = 
            this.tree.new Node(intData[1]);
        CSE12NaryTree<Integer>.Node child2 = 
            this.tree.new Node(intData[2]);  
        CSE12NaryTree<Integer>.Node child3 = 
            this.tree.new Node(intData[3]);  
        CSE12NaryTree<Integer>.Node child4 = 
            this.tree.new Node(intData[4]);  
        CSE12NaryTree<Integer>.Node child5 = 
            this.tree.new Node(intData[5]);

        tree.root = root; 
        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        root.addChild(child4);
        root.addChild(child5);

        root.children = root.getChildren();
        tree.size = 6; 

    }

    /**
     * Creating an 5ary tree with one root
     */
    private void rootTree(){
        CSE12NaryTree<Integer>.Node root = 
            this.tree.new Node(intData[4]);

        tree.root = root;
        tree.size = 1; 
    }

    /**
     * Test helper Method and see if items were sorted in a list 
     * in level order. 
     */
    @Test
    public void levelOrderTest(){
        NaryTree();

        List<CSE12NaryTree<Integer>.Node> levelOrder = 
            new LinkedList<>(); 
        ArrayList<Integer> expected = new ArrayList<>(); 
        expected.add(4); 
        expected.add(3); 
        expected.add(6); 
        expected.add(1); 
        expected.add(2); 
        expected.add(5); 

        levelOrder = this.tree.levelOrder(); 
        for (int i = 0; i < tree.size; i++){
            assertEquals(expected.get(i), 
                levelOrder.get(i).getData());
        }

    }
    /**
     * Tests the add method on a 5-ary tree that already 
     * contains only a root node and its 5 children nodes.
     */
    @Test
    public void testAdd(){
        NaryTree();
        tree.add(11);
        
        assertEquals(1, tree.root.getChildren().get(0).getNumChildren());
        assertEquals((Integer)11, tree.root.getChildren().get(0).getChildren().get(0).getData());
        assertTrue(tree.contains(11));
        assertEquals(5, this.tree.root.getNumChildren());
        assertEquals(7, tree.size);
    }

    /**
     * Tests the contains method on a 5-ary tree when 
     * the element is not present in it. 
     */
    @Test
    public void testContains(){
        NaryTree();
        assertFalse(tree.contains(100)); 
    }

    /**
     * Tests the sortTree method on a 5-ary tree that 
     * contains only the root node.
     */
    @Test
    public void testSortTree(){
        rootTree();
        ArrayList<Integer> expected = new ArrayList<>(); 
        ArrayList<Integer> actual = new ArrayList<>(); 
        actual = tree.sortTree(); 
        expected.add(2); 

        for (int i = 0; i < tree.size - 1; i++){
            assertEquals(expected.get(i), 
                actual.get(i));
        }
    }

    /**
     * Invalid exceptions for add and sort tree inputs 
     */
    @Test
    public void testCustom(){
        NaryTree();

        try {
            tree.add(null);
            //fail
        } catch (NullPointerException e){
            //pass
        }
        try {
            tree.contains(null);
            //fail
        } catch (NullPointerException e){
            //pass
        }
    }

    /**
     * Tests the sortTree method on a 5-ary tree that 
     * contains only the root node.
     */
    @Test
    public void testSortTreeFull(){
        NaryTree();
        ArrayList<Integer> expected = 
            new ArrayList<>(); 
        ArrayList<Integer> actual = 
            new ArrayList<>(); 
        actual = tree.sortTree(); 
        expected.add(1); 
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);

        assertEquals((Integer)1, actual.get(0));
        assertEquals((Integer)2, actual.get(1));
        assertEquals((Integer)3, actual.get(2));
        assertEquals((Integer)4, actual.get(3));
        assertEquals((Integer)5, actual.get(4));
        assertEquals((Integer)6, actual.get(5));

        for (int i = 0; i < tree.size - 1; i++){
            assertEquals(expected.get(i), 
                actual.get(i));
        }
    }
}
