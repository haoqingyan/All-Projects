
/**
 * Project 4 starter file:
 * Dictionary of records with key of Point, satellite data of String.
 * Dictionary is implemented as a splay tree.
 */
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;


/**
 * Point defined by integer Cartesian coordinates.
 *
 * This just stores the two endpoints as plain int coordinates,
 * and as public attributes -- not encapsulated at all.
 */
class Point implements Comparable<Point> {

    /** Cartesian coordinates are just plain public attributes. */
    public int x, y;

    /** Ctor for a point, given its x, y coordinates. */
    public Point(int xx, int yy) {
        x = xx;
        y = yy;
    }

    /** Copy of a point. */
    public Point(Point p) {
        this(p.x, p.y);
    }

    /** Comparison is lexicographical: vertical first, horizontal second. */
    public int compareTo(Point p) {
        final int dy = p.y - y;
        return 0 == dy ? p.x - x : dy;
    }

    /** Stringify in a very plain comma-separated format. */
    public String toString() {
        return "" + x + ", " + y;
    }
}


/**
 * Node of the tree.  It's a standard BST node except for the parent ptr.
 * There are some helper methods here specific to the application.
 */
class Node {

    public Point record;
    public String satellite;
    public Node left, right, parent;

    Node(Point rec, String sat)
    {
        record = rec;
        satellite = sat;
    }

    public String toString()
    {
        return "record = " + record + ", satellite = " + satellite;
    }

    // add more methods as appropriate    
}



/**
 * Splay tree (BST invented by Sleator and Tarjan).
 */
class SplayTree
{
    // tbd
	private Node root; //root node of the splay tree
	
	//constructor
    public SplayTree() {
		super();
		root = null;
	}

    /**
     * a traverse method to represent the debug_print
     * @param SplayNode the splay node
     * @param deep to determine whether add \n 
     * @return
     */
    public String traverse(Node SplayNode, int deep){
    	String ret = "point " + SplayNode.record + " " 
    						+ SplayNode.satellite + " ";
    	// add its left child and right child it has
    	if(SplayNode.left != null) {
    		ret = ret + SplayNode.left.record + " ";
    	}
    	if(SplayNode.right != null) {
    		ret = ret + SplayNode.right.record;
    	}
    	if(deep > 1) ret +=  "\n"; // note that no \n at the first deep 
    	// traverse its left sub tree
    	if(SplayNode.left != null) {
    		ret += traverse(SplayNode.left, deep+1);
    	}
    	// traverse its right sub tree
    	if(SplayNode.right != null) {
    		ret += traverse(SplayNode.right, deep+1);
    	}
    	return ret;
    }
    
	/** Portray tree as a string.  Optional but recommended. */
    public String toString()
    {
        // tbd
    	// utilize traverse method to traverse
    	String ret = traverse(root, 1);
    	return ret;
    }


    /**
     * Search tree for key k.  Return its satellite data if found,
     * and splay the found node.
     * If not found, return null, and splay the would-be parent node.
     */
    public String lookup(Point key)
    {
        // tbd
    	// splay the root node to find key 
    	root = splay(root, key);
    	if(root.record.compareTo(key) == 0){
    		return root.satellite;
    	} else { // not found
    		return null;
    	}
    }

    /**
     * Insert a new record.
     * First we do a search for the key.
     * If the search fails, we insert a new record.
     * Otherwise we update the satellite data with sat.
     * Splay the new, or altered, node.
     */
    public void insert_record(Point key, String sat)
    {
        // tbd
    	// if root is null, insert it to root
    	if(root == null){
    		root = new Node(key, sat);
    		return;
    	}
    	
    	root = splay(root, key);

    	// if the node is already in, update the satellite data with sat.
    	if(root.record.compareTo(key) == 0){
    		root.satellite = sat;
    	}else if(root.record.compareTo(key) > 0){  // new node is smaller than root, add to left 
    		Node insertNode = new Node(key, sat);
    		insertNode.left = root.left;
    		insertNode.right = root;
    		root.left = null;
    		root = insertNode;
    	}else { //new node is larger than root, add to left 
    		Node insertNode = new Node(key, sat);
    		insertNode.left = root;
    		insertNode.right = root.right;
    		root.right = null;
    		root = insertNode;
    	}
    }

    /**
     * Remove a record.
     * Search for the key.  If not found, return null.
     * If found, save the satellite data, remove the record, and splay
     * appropriately.  Use one of the two following methods and update
     * this comment to document which method you actually use.
     *
     * Appropriate method 1:  splay the bereaved parent.
     *   some node x will be deleted, so splay the parent of x.
     * Appropriate method 2:  splay twice.
     *   Find the node u with the key.  Splay u, then immediately splay
     *   the successor of u.  Finally, remove u by splicing.
     *
     * I utilized the method 2, splay twice
     * Return the satellite data from the deleted node.
     */
    public String delete(Point key)
    {
        // tbd
    	if(root == null) return null;
    	root = splay(root, key);
    	//if not exist
    	if(root.record.compareTo(key) != 0){
    		return null;
    	} else { // if root is to be deleted
    		String ret = root.satellite;
    		if(root.left == null){
    			root = root.right;
    		}else{ // choose the max node in the left part
    			Node rightNode = root.right;
    			root = root.left;
    			root = splay(root, key); // splay the max node in the left tree
    			root.right = rightNode;
    		}
    		return ret;
    	}
    }
    
    /**
     * right rotation 
     * @param node root node
     * @return root node after rotation
     */
    private Node zig(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

    /**
     * left rotation 
     * @param node root node
     * @return root node after rotation
     */
    private Node zag(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }
    
    /**
     * splay algorithm, to splay the node with key to the root position
     * if there's no node with key, splay the last searched node is splayed to root position
     * @param rootNode root node
     * @param key key value
     * @return root node with key or last searched node
     */
    private Node splay(Node rootNode, Point key) {
    	
        if (rootNode == null) return null;

        // Judge if key is in the left part or right part or equal the root
        if (key.compareTo(rootNode.record) < 0) {
            if (rootNode.left == null) {
                return rootNode;
            }
            // in the left or right sub tree of the left child
            if (key.compareTo(rootNode.left.record) < 0) {
            	rootNode.left.left = splay(rootNode.left.left, key);
            	rootNode = zig(rootNode); 
            } else if (key.compareTo(rootNode.left.record) > 0) {
            	rootNode.left.right = splay(rootNode.left.right, key);
                if (rootNode.left.right != null)
                	rootNode.left = zag(rootNode.left);
            } 
            // if the left child is null
            if (rootNode.left == null){
            	return rootNode;
            } else {  
            	return zig(rootNode);
            }
        } else if (key.compareTo(rootNode.record) > 0) { 
            if (rootNode.right == null) {
                return rootNode;
            }
            // in the left or right sub tree of the right child
            if (key.compareTo(rootNode.right.record) < 0) {
            	rootNode.right.left  = splay(rootNode.right.left, key);
                if (rootNode.right.left != null)
                	rootNode.right = zig(rootNode.right);
            }
            else if (key.compareTo(rootNode.right.record) > 0) {
            	rootNode.right.right = splay(rootNode.right.right, key);
                rootNode = zag(rootNode);
            } 
            if (rootNode.right == null){
            	return rootNode;
            } else {
            	return zag(rootNode);
            }
        } else { // is the root node is key
        	return rootNode;
        }
    }
}


public class CityRoster {

    /** Print an error message and exit. */
    public static void exit(String message) {
        System.err.println("Error: " + message);
        System.exit(1);
    }

    /** Driver state machine for the roster dictionary. */
    public static void main(String[] argv)
        throws java.io.FileNotFoundException, java.io.IOException
    {
        // Dictionary
        SplayTree roster = new SplayTree();

        // Read from standard input
        BufferedReader br = new BufferedReader(
                                new InputStreamReader(System.in));

        // State machine states
        final int    KEYWORD = 0, XINS = 1, YINS = 2, COLOR = 3,
                    XQRY = 4, YQRY = 5, XDEL = 6, YDEL = 7;
        int state = KEYWORD, xcoord = 0, ycoord = 0;

        // Loop through the lines of input, and divide each line at whitespace:
        for (String line = br.readLine(); line != null; line = br.readLine())
            for (StringTokenizer tz = new StringTokenizer(line);
                    tz.hasMoreTokens(); ) {

                // Next coordinates to be handled
                final String token = tz.nextToken();

                if (KEYWORD == state) {
                    if (0 == token.compareTo("insert"))
                        state = XINS;
                    else if (0 == token.compareTo("query"))
                        state = XQRY;
                    else if (0 == token.compareTo("delete"))
                        state = XDEL;
                    else if (0 == token.compareTo("debug_print")) // optional
                        System.out.println("" + roster);
                    else
                        exit("bad command " + token);
                } else if (XINS == state || XQRY == state || XDEL == state) {
                    xcoord = Integer.parseInt(token);
                    state += 1;
                } else if (YINS == state) {
                    ycoord = Integer.parseInt(token);
                    state = COLOR;
                } else if (COLOR == state) {
                    state = KEYWORD;
                    assert token.length() > 0;
                    roster.insert_record(new Point(xcoord, ycoord), token);
                } else if (YQRY == state) {
                    state = KEYWORD;
                    ycoord = Integer.parseInt(token);
                    String color = roster.lookup(new Point(xcoord, ycoord));
                    System.out.println("query: "
                        + (null == color ? "not found" : color));
                } else if (YDEL == state) {
                    state = KEYWORD;
                    ycoord = Integer.parseInt(token);
                    String color = roster.delete(new Point(xcoord, ycoord));
                    System.out.println("delete: "
                        + (null == color ? "not found" : color));
                } else
                    exit("bad state " + state);
            }
    }
}