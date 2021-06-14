package hackerrank;

public class MergeLinkedList {
	MergeLinkedList prev;
	MergeLinkedList actual;
	MergeLinkedList next;
	Integer value;
	
	public MergeLinkedList() {
		super();
	}
	
	public MergeLinkedList(Integer value) {
		this.value = value;
	}
	
	public MergeLinkedList(Integer ... values) {
		MergeLinkedList newList = null;
		for(int i=0; i<values.length; i++) {
			if(i==0) {
				newList = new MergeLinkedList(values[i]);
			} else {
				newList.next = new MergeLinkedList(values[i]);
				newList.next.prev = newList;
			}
		}
	}
	
	public MergeLinkedList add(MergeLinkedList node) {
		this.next = node;
		node.prev = this;
		return node;
	}
}
