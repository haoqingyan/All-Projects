//@Author  Haoqing Yan
public class OrderedSet<E extends Comparable<E>> {
	private class TreeNode {
		private E data;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(E reference) {
			data = reference;
			left = null;
			right = null;
		}
	}

	private TreeNode root;

	public OrderedSet() {
		root = null;
	}

	// 1) Add element to this OrderedSet and return true keeping this a
	// OrderedSet.
	// If element is found to already exist, do not change this OrderedSet,
	// return false.

	public boolean insert(E element) {
		// newElement will be added and this will still be a BinarySearchTree.
		// This tree will not insert newElement if it will compareTo an existing
		// element equally.
		if (root == null) {
			root = new TreeNode(element);

		} else {
			// find the proper leaf to attach to
			TreeNode curr = root;
			TreeNode prev = root;
			while (curr != null) {
				prev = curr;
				if (element.compareTo(curr.data) < 0)
					curr = curr.left;
				else if (element.compareTo(curr.data) > 0)
					curr = curr.right;
				else {
					return false;
				}
			}
			// Determine whether to link the new node came from prev.left or
			// prev.right
			if (element.compareTo(prev.data) < 0)
				prev.left = new TreeNode(element);
			else
				prev.right = new TreeNode(element);
		}
		return true;
	}

	// 2) The number of elements in this OrderedSet, which should be 0 when
	// first constructed.
	// This may run O(n) even though it could be O(1). Do not use a variable n
	// for #elements.
	public int size() {
		return size(root);
	}

	private int size(TreeNode t) {
		if (t == null) {
			return 0;
		} else {
			return 1 + size(t.left) + size(t.right);
		}
	}

	// 3) Return one string that concatenates all elements in this OrderedSet as
	// they are
	// visited in order. Elements are separated by spaces as in "1 4 9" from
	// this OrderedSet:
	// 4
	// / \
	// 1 9
	public String toStringInorder() {
		return toString(root);
	}

	private String toString(TreeNode t) {
		String result = "";
		if (t != null) {
			result += toString(t.left) + " ";
			result += t.data;
			result += " " + toString(t.right);
		}
		return result.trim();
	}

	// 4) Return true is search equals an element in this OrderedSet.
	public boolean contains(E search) {
		TreeNode temp = root;
		while (temp != null) {
			if (search.compareTo(temp.data) < 0) {
				temp = temp.left;
			} else if (search.compareTo(temp.data) > 0) {
				temp = temp.right;
			} else {
				return true;
			}
		}
		return false;
	}

	// 5) Return the element in this OrderedSet that is greater than all other
	// elements.
	// If this OrderedSet is empty, return null.
	public E max() {
		if (root == null) {
			return null;
		}
		TreeNode temp = root;
		while (temp.right != null) {
			temp = temp.right;
		}
		return temp.data;
	}

	// 6) Return how many nodes are at the given level. If level > the height of
	// the tree,
	// return 0. Remember that an empty tree has a height of -1 (see page 252).
	//
	// 4 There is 1 node on level 0
	// / \
	// 3 9 There are 2 nodes on level 1
	// / / \
	// 1 5 11 There are 3 nodes in level 2 (and 0 nodes on levels >= 3)
	public int nodesAtLevel(int level) {
		return nodesAtLevel(root, level);
	}

	private int nodesAtLevel(TreeNode t, int level) {
		if (t == null || level == -1) {
			return 0;
		} else if (level == 0) {
			return 1;
		} else {
			return nodesAtLevel(t.left, level - 1)
					+ nodesAtLevel(t.right, level - 1);
		}
	}

	// 7) Return the intersection of this OrderedSet and the other OrderedSet as
	// a new OrderedSet. Do not modify this OrderedSet or the other OrderedSet.
	// The intersection of two sets is the set of elements that are in both
	// sets.
	// The intersection of {2, 4, 5, 6} and {2, 5, 6, 9} is {2, 5, 6}
	public OrderedSet<E> intersection(OrderedSet<E> other) {
		OrderedSet<E> newtree = new OrderedSet<E>();
		intersection(newtree, root, other.root);
		return newtree;
	}

	private void intersection(OrderedSet<E> result, TreeNode r1, TreeNode r2) {
		if (r1 == null || r2 == null) {
			return;
		} else if (r1.data.compareTo(r2.data) == 0) {
			result.insert(r1.data);
			intersection(result, r1.left, r2.left);
			intersection(result, r1.right, r2.right);
		} else if (r1.data.compareTo(r2.data) < 0) {
			intersection(result, r1.right, r2);
			intersection(result, r1, r2.left);
		} else {
			intersection(result, r1.left, r2);
			intersection(result, r1, r2.right);
		}

	}

	// 8) Return the union of this OrderedSet and the other OrderedSet as
	// a new OrderedSet. Do not modify this OrderedSet or the other OrderedSet.
	// The union of two sets is the set all distinct elements in the
	// collection.[
	// The union of {2, 4, 6} and {2, 5, 9} is {2, 4, 5, 6, 9}
	public OrderedSet<E> union(OrderedSet<E> other) {
		OrderedSet<E> newtree = new OrderedSet<E>();
		if (this.root == null && other.root == null) {
			return newtree;
		}
		if (this.root == null) {
			return other;
		} else if (other.root == null) {
			return this;
		} else {
			union(newtree, root, other.root);
		}
		return newtree;

	}

	private void union(OrderedSet<E> tempTree, TreeNode r1, TreeNode r2) {
		if (r1 != null && r2 != null) {
			tempTree.insert(r1.data);
			union(tempTree, r1.left, r2);
			union(tempTree, r1.right, r2);

			if (!tempTree.contains(r2.data)) {
				tempTree.insert(r2.data);
			}
			union(tempTree, r1, r2.left);
			union(tempTree, r1, r2.right);
		}
	}

	// 9) Return an OrderedSet that contains all elements that are greater than
	// or equal to
	// the first parameter (inclusive) and strictly less than the second
	// parameter (exclusive).
	public OrderedSet<E> subset(E inclusive, E exclusive) {
			 tempOrderedSet=new OrderedSet<E>();
			 subset(root,inclusive,exclusive);
			 return tempOrderedSet;
			
		 }
		 private void subset(TreeNode temp,E inclusive, E exclusive){
			 if ( temp == null ){
				 return;
			 }else{
				   subset(temp.left,inclusive, exclusive);
				  
				 if(temp.data.compareTo(inclusive)>=0&&temp.data.compareTo(exclusive)<0) {
					 tempOrderedSet.insert(temp.data);
				 }
			
				   subset(temp.right,inclusive, exclusive);
		     }
		 }
	

	// 10) Return true if two different OrderedSet objects have the same exact
	// structure.
	// Each node must have the same number of nodes on every level, the same
	// height,
	// the same size, the same number of leaves, and the same number of internal
	// nodes.
	// Each corresponding node must also have the same number of children (0, 1,
	// or 2)
	// in the same place. The data need NOT be the same. Do not compare
	// corresponding
	// elements. Each of these pairs of OrderedSets have the sameStructure:
	//
	// M P | Lmn Rts | 5 55 | 3 999
	// / \ / \ | / / | \ \ | / /
	// B R F Q | Abc Lmn | 10 89 | 2 888
	// \ \ \ \ | | / \ / \ | / /
	// F Z J R | | 8 77 79 99 | 1 777
	//
	// Empty trees also have the same structure.
	//
	// Each pair of these OrderedSets do NOT have the sameStucture (elements may
	// be "equals")
	//
	// M M | M M | 5 5 | 3 2
	// / \ / \ | / / | \ \ | / / \
	// B R B Z | L A | 10 12 | 2 1 3
	// \ \ \ / | / \ | / \ / | /
	// F Z F R | A L | 8 12 10 | 1
	// | | / |
	// | | 8 |
	//
	// Precondition: E is the same for both OrderedSets
	public boolean sameStructure(OrderedSet<E> other) {
		if (other.size() != this.size()) {
			return false;
		} else {
			return same(other, root, other.root);
		}
	}

	private boolean same(OrderedSet<E> other, TreeNode r1, TreeNode r2) {

		if (r1 == null && r2 != null) {
			return false;
		} else if (r1 != null && r2 == null) {
			return false;
		} else if (r1 == null && r2 == null) {
			return true;
		} else {
			return same(other, r1.left, r2.left)
					&& same(other, r1.right, r2.right);
		}
	}

	// 11) If element equals an element in this OrderedSet, remove it and return
	// true.
	// Return false whenever element is not found. In all cases, this OrderedSet
	// must
	// remain a true OrderedSet. Here is one recommended algorithm
	// http://www.cs.arizona.edu/~mercer/Projects/BSTRemoveGeneric.pdf
	private Boolean TorF;
	OrderedSet<E> tempOrderedSet;
	public boolean remove(E element) {
		TorF = false;
	    tempOrderedSet = new OrderedSet<E>();
		helpremove(root, element);
		if (TorF == true) {
			root = tempOrderedSet.root;
			
		}
		return TorF;

	}

	private void helpremove(TreeNode temp, E element) {
		if (temp == null) {
			return;
		} else {
			helpremove(temp.left, element);

			if (!temp.data.equals(element)) {
				tempOrderedSet.insert(temp.data);
			} else {
				TorF = true;
			}

			helpremove(temp.right, element);
		}
	}

}