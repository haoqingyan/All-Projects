package code;
import java.util.NoSuchElementException;

/**
 *
 * The linked list implementation for storing the large number
 *
 * The class should be able to instantiate a LLInt from an ordinary int,
 * or from a String of decimal digits (e.g., "5280" interpreted the usual way).
 * You can throw exceptions for bad inputs.
 *
 * The class should have a toString() method (where StringBuffer might be useful)
 * to produce a decimal representation.  You might want to implement clone(),
 * or make copies some other way, e.g., with a copy constructor -- your choice.
 * Definitely the class should have a multiplication method and an addition method,
 * each returning a new LLInt object.
 *
 * There are several helper methods, such as check, negate, get and so on,
 * each of them associate with the main method.
 *
 */
public class LLInt implements Comparable {
    private Node firstNode;// the first node
    private Node lastNode; // the last node
    private int size; // the size of the list
    private boolean isNegative;
    private static final int RADIX = 10; // constant 10
    
    /**
     *
     * The inner class Node
     *
     */
    public class Node {
        int data; // the data of the node
        Node next; // the next node
        
        /**
         * the constructor for Node
         *
         */
        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
        
        /**
         * get the data
         *
         * @return the data
         */
        public int getData() {
            return data;
        }
        
        /**
         * set the data
         *
         * @return data the data to be set
         */
        public void setData(int data) {
            this.data = data;
        }
        
        /**
         * get the next node
         *
         * @return the next node
         */
        public Node getNext() {
            return next;
        }
        
        /**
         * set the next node
         *
         * @return next the next node to be set
         */
        public void setNext(Node next) {
            this.next = next;
        }
    }
    
    /**
     *
     * The constructor for LLInt
     *
     */
    public LLInt() {
        firstNode = null;
        lastNode = null;
        size = 0;
        isNegative = false;
    }
    
    /**
     *
     * The constructor for LLInt from a String
     *
     * @param token
     *            the string of token
     */
    public LLInt(String token) {
        this();
        if (token.startsWith("-")) {
            isNegative = true;
            token = token.substring(1);
        }
        
        while (token.length() > 1 && token.charAt(0) == '0') {
            token = token.substring(1);
        }
        for (int i = 0; i < token.length(); i++) {
            try {
                int number = Integer.parseInt(token.charAt(token.length() - i - 1) + "");
                this.append(number);
            } catch (Exception e) {
                System.err.println("Error:  Illegale Input. The token must be integer.");
                System.exit(1);
            }
        }
    }
    
    /**
     *
     * The constructor for LLInt from a int
     *
     * @param token
     *            the int value of token
     */
    public LLInt(int token) {
        this(token + "");
    }
    
    /**
     *
     * add two nonnegative integers correctly, and return the sum.
     *
     * @param token2
     *            the number to be added
     * @return sum the sum
     */
    private LLInt add_nonneg_ints(LLInt token2) {
        StackCalc calculator = new StackCalc();
        LLInt result = calculator.add(this, token2);
        check(result);
        return result;
        
    }
    
    /**
     *
     * add two integers correctly, and return the sum.
     *
     * @param that
     *            the number to be added
     * @return sum the sum
     */
    public LLInt add(LLInt that) {
        LLInt result = new LLInt();
        if (this.isNegative == that.isNegative) {
            result = add_nonneg_ints(that);
            if (this.isNegative) {
                result.negate();
            }
        } else {
            result = subtract_nonneg_ints(that);
            if (this.isNegative) {
                result.negate();
            }
        }
        check(result);
        return result;
        
    }
    
    /**
     *
     * subtract two integers correctly, and return the difference.
     *
     * @param that
     *            the number to be subtracted
     * @return the difference
     */
    public LLInt subtract(LLInt that) {
        LLInt result = new LLInt();
        if (this.isNegative != that.isNegative) {
            result = add_nonneg_ints(that);
            if (this.isNegative) {
                result.negate();
            }
        } else {
            result = subtract_nonneg_ints(that);
            if (this.isNegative) {
                result.negate();
            }
        }
        check(result);
        return result;
        
    }
    
    /**
     *
     * add two nonnegative integers correctly, and return the sum.
     *
     * @param token2
     *            the number to be subtracted
     * @return result the result
     */
    private LLInt subtract_nonneg_ints(LLInt token2) {
        LLInt result = new LLInt();
        LLInt p1;
        LLInt p2;
        int size1;
        int size2;
        boolean needNegate = false;
        if (this.compareTo(token2) >= 0) {
            p1 = this;
            size1 = p1.size();
            p2 = token2;
            size2 = p2.size();
        } else {
            p1 = token2;
            size1 = p1.size();
            p2 = this;
            size2 = p2.size();
            needNegate = true;
        }
        int carry = 0; // the integer used for increasing the weight as power of
        // 10
        for (int i = 0; i < size1 && i < size2; i++) {
            int temp = p1.get(i) - p2.get(i) - carry;
            if (temp < 0) {
                temp = temp + RADIX;
                carry = 1;
            } else {
                carry = 0;
            }
            result.append(temp);
        }
        for (int i = size2; i < size1; i++) {
            int temp = p1.get(i) - carry;
            if (temp < 0) {
                temp = temp + RADIX;
                carry = 1;
            } else {
                carry = 0;
            }
            result.append(temp);
        }
        // remove unnecessary '0'
        while (result.size() > 1 && result.get(result.size() - 1) == 0) {
            result.removeLast();
        }
        if (needNegate) {
            result.negate();
        }
        check(result);
        return result;
    }
    
    /**
     * this method takes the given number and places it at the end of the list
     *
     * @param data
     *            the new data to be appended
     */
    public void append(int newData) {
        if (firstNode == null) {
            firstNode = lastNode = new Node(newData, null);
            size++;
        } else {
            Node newLastNode = new Node(newData, null);
            lastNode.setNext(newLastNode);
            lastNode = newLastNode;
            size++;
        }
    }
    
    /**
     * this method remove the last node
     *
     */
    public void removeLast() {
        if (firstNode == null)
            throw new NoSuchElementException();
        Node temp = firstNode;
        for (int k = 0; k < size - 1; k++)
            temp = temp.next;
        temp.next = null;
        lastNode = temp;
        size--;
    }
    
    /**
     * return the size of the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }
    
    /**
     * get the data with the given index
     *
     * @param index
     *            the index
     * @return the data
     */
    public int get(int index) {
        if (firstNode == null)
            throw new NoSuchElementException();
        Node temp = firstNode;
        for (int k = 0; k < index; k++)
            temp = temp.next;
        if (temp == null)
            throw new IndexOutOfBoundsException();
        return temp.data;
    }
    
    /**
     * This method is to produce a decimal representation of the list
     * 
     * @return string decimal representation
     */
    public String toString() {
        StringBuffer strBuffer = new StringBuffer();
        if (isNegative) {
            strBuffer.append("-");
        }
        for (int i = 0; i < size; i++) {
            strBuffer.append(get(size - i - 1));
        }
        return strBuffer.toString();
    }
    
    /**
     * This method will make the LLInt toggleits algebraic sign.
     * 
     */
    public void negate() {
        isNegative = !isNegative;
    }
    
    /**
     * This method will check the LLint to avoid "-0" and correct it
     * 
     * @param token
     *            the token to be checked and modified
     * 
     */
    public void check(LLInt token) {
        if (token.isNegative && token.size == 1 && token.get(0) == 0) {
            token.negate();
        }
    }
    
    /**
     * This method is to compare the absolute value of this with that
     * 
     * @return negative if this this is smaller than; positive if this this is
     *         bigger than that; 0 if this and that are equals
     */
    public int compareTo(Object that) {
        LLInt p2 = (LLInt) that;
        if (this.size() > p2.size) {
            return 1;
        } else if (this.size() < p2.size) {
            return -1;
        } else {
            for (int i = 0; i < this.size(); i++) {
                int n1 = this.get(this.size()-i-1);
                int n2 = p2.get(this.size()-i-1);
                if (n1 != n2) {
                    return n1 - n2;
                }
            }
        }
        return 0;
    }
    
    public void adjustSelf(){
    	int t = 0;
    	for(Node node = this.firstNode; node != null; node = node.next){
    		int tmp = node.data + t;
    		node.data = tmp%RADIX;
    		t = tmp/RADIX;
    	}
    	while(t > 0){
    		this.append(t%RADIX);
    		t /= RADIX;
    	}
    	
    }
    
    public LLInt multi_single_node(Node node){
    	LLInt res = new LLInt();
    	for(int i = 0; i < this.size; i++){
    		res.append(node.getData()*this.get(i));
    	}
    	res.isNegative = this.isNegative;
    	res.adjustSelf();
    	return res;
    }
    
	public LLInt es_multiply(LLInt y) {
		// TODO Auto-generated method stub
		if(y.size == 0 || this.size == 0){
			return new LLInt();
		}
		LLInt res = this.multi_single_node(y.firstNode);
		int i = 1;
		for(Node node = y.firstNode.next; node != null; node = node.next){
			res = res.add((this.multi_single_node(node)).leftMove(i));
			i++;
		}
		if(y.isNegative){
			res.isNegative = !res.isNegative;
		}
		return res;
		
	}

	public LLInt ko_multiply(LLInt y){
		System.out.println("x is " + this);
		System.out.println("y is " + y);
		LLInt res = new LLInt();
		if(y.size == 0 || this.size == 0){
			return new LLInt();
		}
		if(y.size == 1){
			res = this.multi_single_node(y.firstNode);
			if(y.isNegative){
				res.isNegative = !res.isNegative;
			}
			return res;
		}
		if(this.size == 1){
			res = y.multi_single_node(this.firstNode);
			if(y.isNegative){
				res.isNegative = !res.isNegative;
			}
			return res;
		}
		
		LLInt partA = new LLInt();
		LLInt partB = new LLInt();
		LLInt partC = new LLInt();
		LLInt partD = new LLInt();
		
		int nDigit = y.size, t;
		if(nDigit > this.size){
			t = this.size/2;
		}else{
			t = nDigit / 2;
		}
		this.split(partA, partB, t);
		y.split(partC, partD, t);
		System.out.println("4 part is "+partA+" "+partB+" "+partC +" "+partD);
		
		LLInt partAC = partA.ko_multiply(partC);
		//System.out.println("PartAC is " + partAC);
		LLInt partBD = partB.ko_multiply(partD);
		//System.out.println("PartBC is " + partBD);
		LLInt partF = (partA.subtract(partB)).ko_multiply(partC.subtract(partD));
		//System.out.println("PartF is " + partF);
		
		// AC+BD-F
		LLInt partG = (partAC.add(partBD)).subtract(partF);
		//System.out.println("PartG is " + partG);
		partAC.leftMove(2*t);
		partG.leftMove(t);

		System.out.println("3 part is" + partAC + " " + partG + " " + partBD);
		res = (partAC.add(partG)).add(partBD);
		if(this.isNegative == y.isNegative){
			res.isNegative = false;
		}else{
			res.isNegative = true;
		}
		return res;
	}
	
	/*
	 * split the number as two part, ignore whether it is negative
	 * @param t - the number of digit for second part
	 */
	public void split(LLInt firstPart, LLInt secondPart, int t){
		if(size == 0 || size == 1){
			throw new NoSuchElementException();
		}
		Node node = this.firstNode;
		for(int i = 0; i < t; i++){
			secondPart.append(node.data);
			node = node.next;
		}
		
		for(int i = t; i < size; i++){
			firstPart.append(node.data);
			node = node.next;
		}
	}
	
	private LLInt leftMove(int n) {
		// TODO Auto-generated method stub
		for(int i = 0; i < n;i ++){
			Node node = new Node(0, firstNode);
			firstNode = node;
			this.size++;
		}
		return this;
	}
}
