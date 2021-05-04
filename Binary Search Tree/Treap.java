
import java.util.*;
import java.lang.*;

public class Treap<E extends Comparable<E>> {
	
	//create private static inner class Node
	private static class Node<E>{
		
		//Data Fields
		public E data; //key for search
		public int priority; //random heap priority
		public Node<E> left; //left child node
		public Node<E> right; //right child node
		
		//constructors
		public Node(E data, int priority) {
			
			//throw exceptions when data is null
			if( data == null)
				throw new NullPointerException("No Data");
			this.data = data;
			this.priority = priority;
			
			//set child node as null
			this.left = null;
			this.right = null;				
	}
		
		//Methods
		public Node<E> rotateRight(){
			
			Node<E> temp = this.left;
			this.left = temp.right;
			temp.right = this;
			return temp;						
		}
		
        public Node<E> rotateLeft(){
			
			Node<E> temp = this.right;
			this.right = temp.left;
			temp.left = this;
			return temp;						
		}
}
	
	//Create the Treap Class
	  //Data Fields
	private Random priorityGenerator;
	private Node<E> root; //E must be Comparable
	
	//Constructor
	public Treap() {
		this.root = null;
		this.priorityGenerator = new Random();
		
	}
	
	public Treap(long seed) {
		this.root = null;
		this.priorityGenerator = new Random(seed);
		
	}
	
	//methods
	public boolean add(E key) {
		//check whether there is already a node containing the given key
		if(find(key))
			
			return false;
		
		else {
			int priority_create = priorityGenerator.nextInt();
			return add(key, priority_create);
		}
		
	}
	
	public boolean add(E key, int priority) {
		
		if(this.root == null) {
			root = new Node<E>(key, priority);
			return true;
		}
		
		if(find(key)) {
			return false;
		}
		
		Stack<Node<E>> parameter = new Stack<Node<E>>();
		Node<E> temp = root;
		
		while(temp != null) {
			if(key.compareTo(temp.data) == 0) {
				break;
			}
			if(key.compareTo(temp.data) < 0) {
				parameter.push(temp);
				if(temp.left ==null) {
					temp.left = new Node<E>(key, priority);
					temp = temp.left;
				}
				else
					temp = temp.left;
			}
				else {
					parameter.push(temp);
					if(temp.right ==null) {
						temp.right = new Node<E>(key, priority);
						temp = temp.right;
					}
					else
						temp = temp.right;					
				}
			}
		reheap(temp, parameter);
		return true;
		}
	
	private void reheap(Node<E> temp, Stack<Node<E>> parameter) {
		
		if(parameter.empty()) {
			root = temp;
			return;
		}
		else if(temp.priority < parameter.peek().priority) {
			return;

		}
		else {
			Node<E> parpop = parameter.pop();
			
			if(parpop.left != null && parpop.left.equals(temp)) {
				
				parpop.rotateRight();
				
			}
			else {
				parpop.rotateLeft();
			}
			
			if(!parameter.empty()) {
				Node<E> parpeek = parameter.peek(); 
				
				if(parpeek.left != null && parpeek.left.equals(parameter.pop())) {
					parpeek.left = temp;
				}
				else {
					parpeek.right = temp;
				}
			}
			
		reheap(temp, parameter);
		}
	}
	
	
	
	public boolean delete(E key) {
		
        Stack<Node<E>> parameter = new Stack<Node<E>>();
		
		if(root == null || find(key) == false) {
			return false;
		}
		
		if (root.data.equals(key)){
			
            if (root.left != null && root.right != null) {
            	
                if (root.left.priority > root.right.priority) {
                    root.rotateRight();
                } else {
                    root.rotateLeft();
                }
            }
            if (root.left != null && root.right == null) {
            	
                root = root.rotateRight();
            }
            if (root.right != null && root.left == null) {
            	
                root = root.rotateLeft();
            }
        }
       
        return deletehelper(key, root, parameter);
		
	}
	
	 private boolean deletehelper(E key, Node<E> temp, Stack<Node<E>> parameter) {
	        boolean deleted = false;

	        int res = key.compareTo(temp.data);
	        
	        if (res < 0) { 
	            parameter.push(temp);
	            return deletehelper(key, temp.left, parameter);
	        }
	        if (res > 0 ) { 
	            parameter.push(temp);
	            return deletehelper(key, temp.right, parameter);
	        }
	        if (res == 0) { 
	        	
	            while (true) {
	            	
	                if (temp.left == null && temp.right == null) {
	                    break;
	                }
	                
	                if (temp.left != null && temp.right != null) {
	                	
	                    if (temp.left.priority > temp.right.priority) {
	                    	
	                        Node<E> parent = parameter.pop();
	                        
	                        if (parent.right.equals(temp)) {
	                            parent.right = temp.rotateRight();
	                            parameter.push(parent.right); 
	                        } else {
	                            parent.left = temp.rotateRight();
	                            parameter.push(parent.left); 
	                        }
	                    } else {
	                        Node<E> parent = parameter.pop();
	                        if (parent.right.equals(temp)) {
	                            parent.right = temp.rotateLeft();
	                            parameter.push(parent.right);
	                        } else {
	                            parent.left = temp.rotateLeft();
	                            parameter.push(parent.left);
	                        }
	                    }
	                }
	                if (temp.left != null && temp.right == null) {
	                    Node<E> parent = parameter.pop();
	                    if (parent.right != null && parent.right.equals(temp)) {
	                        parent.right = temp.rotateRight();
	                        parameter.push(parent.right); 
	                    } else {
	                        parent.left = temp.rotateRight();
	                        parameter.push(parent.left); 
	                    }
	                }
	                if (temp.right != null && temp.left == null) {
	                    Node<E> parent = parameter.pop();
	                    if (parent.right != null && parent.right.equals(temp)) {
	                        parent.right = temp.rotateLeft();
	                        parameter.push(parent.right);
	                    } else {
	                        parent.left = temp.rotateLeft();
	                        parameter.push(parent.left);
	                    }
	                }
	            }
	            Node<E> parent2 = parameter.peek();
	            if (parent2.left != null && parent2.left.equals(temp)) {
	                parent2.left = null;
	                deleted = true;
	                return deleted;
	            } else {
	                parent2.right = null;
	                deleted = true;
	                return deleted;
	            }
	        }
	        return true; 
	    }
	
	private boolean find(Node<E> root, E key) {
		
		if(root == null) {
			return false;
			
		}
		else {
			int res = key.compareTo(root.data);
			
			if(res == 0) {
				return true;
			}
			else if(res <0) {
				return find(root.left, key);
			}
			else {
				return find(root.right, key);
			}
		}
		
	}
	
	public boolean find(E key) {
		if(find(root, key) != false) {
			
			return find(root, key);
		}
		else
			return false;
	}
	
	public String toString() {
		
		StringBuilder res = new StringBuilder();
		
		preorderTraversal(root, 1, res);
		return res.toString();
		
	}
	
	public void preorderTraversal(Node<E> n, int depth, StringBuilder res) {
		
		for (int i = 1; i < depth; i++) {
			res.append("  ");
		}
		if (n == null) {
			res.append("null\n");
		} 
		else {
			res.append("(key="+n.data.toString()+", "+"priority="+n.priority+")"+"\n");
			
			preorderTraversal(n.left, depth + 1, res);
			preorderTraversal(n.right, depth + 1, res);
		}
}
	
	public static void main(String args[]) {
		Treap<Integer> testTree = new Treap<Integer>();		
		testTree.add(4, 19);
		testTree.add(2, 31);		
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		testTree.add(6, 70);
		System.out.println(testTree);
		testTree.delete(1);
		System.out.println();
		System.out.println(testTree);

	}
	
	}
