

import java.util.*;

public class IDLList<E> {
	
	//Class IDLList<E> includes four data field
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices; //an array-based list of references to nodes
	
	//private inner class Node<E>
	private static class Node<E>{
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		//a constructor that creates a node holding elem
		public Node(E elem) {
			this.data = elem;
			this.next = null;
			this.prev = null;
		}
		
		//a constructor that crates a node holding elem, with next as next and pre as prev
		public Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	
	//create an empty double-linked list
	public IDLList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.indices = new ArrayList<Node<E>>();		
	}
	
	//add elem at position index
	public boolean add(int index, E elem) {
		if(index <0 || index >= size) {
			throw new IllegalArgumentException("Out of Range");
		}
		else if (index ==0) {
			this.add(index, elem);
		}
		else {
			Node<E> position = head;
			for(int i=0;i<index;i++) {
				position = position.next;
			}
			Node<E> addnode = new Node<E>(elem,position,position.next);
			position.next = addnode;
			addnode.next.prev = addnode;
			indices.add(index,addnode);
			size++;
			
		}
		return true;
	}
	
	//adds elem at the head
	public boolean add(E elem) {
		if(head == null) {
			head = new Node(elem, null, null);
			tail = head;
			size++;
			indices.add(0,head);
			return true;
			
		}
		else {
			Node<E> addnode = new Node<E>(elem,null,head);
			head.prev = addnode;
			head = addnode;
			indices.add(0, head);
			size++;
			return true;
		}
	}
	
	//add elem as the new last element of the list
	public boolean append(E elem) {
		if(size == 0 || head == null) {
			head = new Node(elem, null, null);
			tail = head;
			size++;
			indices.add(0,tail);
			return true;			
		}
		else {
			Node<E> position = tail;
			Node<E> addnode = new Node<E>(elem, position, null);
			tail.next = addnode;
			tail = addnode;
			size++;
			indices.add(size-1,tail);
			return true;
		}		
	}
	
	//return the object at position index from the head
	public E get(int index) {
		if(index <0 || index >=size || head == null) {
			throw new IllegalArgumentException("Out of Range");
		}
		else {
			return indices.get(index).data;
		}
	}
	
	//return the object at the head
	public E getHead() {
		if(head == null && tail == null) {
			throw new IllegalArgumentException("No head");
		}
		else {
			return head.data;
		}
	}
	
	//return the object at the tail
	public E getLast() {
		if(head == null && tail == null) {
			throw new IllegalArgumentException("No tail");
		}
		else {
			return tail.data;
		}
	}
	
	//return the list size
	public int size() {
		return this.size;
	}
	
	//remove and return the element at the head
	public E remove() {
		if(head == null) {
			throw new IllegalArgumentException("No head");
		}
		else {
			Node<E> prehead = head;
			head = head.next;
			head.prev = null;
			size = size-1;
			indices.remove(0);
			return prehead.data;
		}
	}
	
	//remove and return the element at the tail
	public E removeLast() {
		if(tail == null) {
			throw new IllegalArgumentException("No tail");
		}
		else {
			Node<E> pretail = tail;
			tail = tail.prev;
			tail.next = null;
			size = size-1;
			indices.remove(size);
			return pretail.data;
		}
	}
	
	//remove and return the element at the index index
	public E removeAt(int index) {
		if(head == null || index <0 || index >=size) {
			throw new IllegalArgumentException("Out of range");
		}
		else if(index == 0) {
			return remove();
		}
		else if(index == size-1) {
			return removeLast();
		}
		else {
			Node<E> position = indices.get(index);
			position.prev.next = position.next;
			position.next.prev = position.prev;
			size = size-1;
			indices.remove(index);
			return position.data;
		}
	}
	
	//remove the first occurrence of elem in the list and return true, return false while not
	public boolean remove(E elem) {
		if (head == null) {
			throw new IllegalArgumentException("List Not exist");
		}
		else {
			int n = 0;
			Node<E> check = head;
			while(check !=null && check.data != elem) {
				
				check = check.next;
				n++;			
			}
			
			if(n == size) {
				return false;
			}
			else {
				removeAt(n);
				return true;
			}			
		}
	}
	
	//present a string representation of the list
	public String toString() {
		if(head == null) {
			throw new IllegalArgumentException("List Not exist");
		}
		else {
			StringBuilder nodestring = new StringBuilder();
			Node<E> position = head;
			while(position !=null) {
				nodestring.append("["+position.data+"]");
				position = position.next;
			}
			return nodestring.toString();
		}		
	}
}
