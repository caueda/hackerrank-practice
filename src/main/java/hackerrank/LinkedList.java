package hackerrank;

import java.util.List;

public class LinkedList {
	LinkedList prev;
	LinkedList next;
	LinkedList tail;
	Integer value;
	
	public LinkedList() {
		super();
	}
	
	public LinkedList(int value) {
		this.value = value;
	}
	
	public LinkedList(int[] values) {
		for(int i=0; i<values.length; i++) {
			if(i==0) {
				tail = this;
				tail.value = values[i];
			} else {
				tail.next = new LinkedList(values[i]);
				tail.next.prev = tail;
				tail = tail.next;
			}
		}
	}
	
	public LinkedList(List<Integer> values) {
		this(values.stream().mapToInt(Integer::intValue).toArray());
	}

	public LinkedList add(LinkedList node) {
		this.next = node;
		node.prev = this;
		return node;
	}
	
	public String toString() {
		StringBuffer value = new StringBuffer();
		value.append(readNodes(this));
		return value.toString();
	}
	
	public String toStringReverse() {
		StringBuffer value = new StringBuffer();
		value.append(readNodesReverse(this.tail));
		return value.toString();
	}
	
	private String readNodes(LinkedList node) {
		if(node.next == null) {
			return node.value.toString();
		} else {
			return node.value + ", " + readNodes(node.next);
		}
	}
	
	private String readNodesReverse(LinkedList node) {
		if(node.prev == null) {
			return node.value.toString();
		} else {
			return node.value + ", " + readNodesReverse(node.prev);
		}
	}
	
	public static LinkedList merge(LinkedList list1, LinkedList list2) {
		LinkedList m = null;
		LinkedList actual = null;
		LinkedList h1 = (list1 != null) ? list1 : null;
		LinkedList h2 = (list2 != null) ? list2 : null;
		
		if(h1 == null) {
			return copyLinkedList(h2);
		} else if(h2 == null) {
			return copyLinkedList(h1);
		}
		
		if(h1.value.intValue() < h2.value.intValue()) {
			m = copyNode(h1);
			h1 = h1.next;
		} else {
			m = copyNode(h2);
			h2 = h2.next;
		}
		actual = m;
		
		while(h1 != null || h2 != null) {
			if(h1 == null) {
				actual.next = copyNode(h2);
				actual.next.prev = actual;
				return m;
			} else if(h2 == null) {
				actual.next = copyNode(h1);
				actual.next.prev = actual;
				return m;
			}
			
			if(h1.value.intValue() < h2.value.intValue()) {
				actual.next = copyNode(h1);
				h1 = h1.next;
			} else {
				actual.next = copyNode(h2);
				h2 = h2.next;
			}
			actual = actual.next;
		}
		
		return m;
	}
	
	public static LinkedList copyNode(LinkedList node) {
		return new LinkedList(node.value);
	}
	
	public static LinkedList copyLinkedList(LinkedList node) {
		LinkedList newLinkedList = null;
		while(node != null) {
			if(newLinkedList == null) {
				newLinkedList = new LinkedList(node.value);
			} else {
				newLinkedList.next = new LinkedList(node.value);
				newLinkedList.next.prev = newLinkedList;
				newLinkedList = newLinkedList.next;
			}
		}
		return newLinkedList;
	}
}
