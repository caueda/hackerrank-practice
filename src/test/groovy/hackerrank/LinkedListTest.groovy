package hackerrank

import spock.lang.Specification

class LinkedListTest extends Specification {
	def "create a new LinkedList from int array" () {
		given:
			int[] input =  [1,2,3,4,5,6,7]
		when:
			LinkedList linkedList = new LinkedList(input)
		then:
			linkedList.toString() == "1, 2, 3, 4, 5, 6, 7"
	}
	
	def "test toStringReverse" () {
		given:
			int[] input =  [1,2,3,4,5,6,7]
		when:
			LinkedList linkedList = new LinkedList(input)
		then:
			linkedList.toStringReverse() == "7, 6, 5, 4, 3, 2, 1"
	}
	
	def "merge two LinkedList" () {
		given:
			LinkedList linkedList1 = new LinkedList([1, 3])
			LinkedList linkedList2 = new LinkedList([2, 4])
		when:
			LinkedList linkedList = LinkedList.merge(linkedList1, linkedList2)
		then:
			linkedList.toString() == "1, 2, 3, 4"
	}
}