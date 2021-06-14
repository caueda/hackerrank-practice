package hackerrank

import spock.lang.Specification

class MissingNumberTest extends Specification {
	def "find missing number from input" () {
		given:
			int[] input =  [3, 7, 1, 2, 8, 4, 5]
		when:
			int calculatedMissingResult = MissingNumber.find(input)
		then:
			calculatedMissingResult == 6
	}
	
	def "run a datatable test" (int[] input, int result) {
		expect: MissingNumber.find(input) == result
		where:
			input | result
			[7,2,9,4,5,1,6,8] | 3
			[7,2,4,5,3,1,6,8] | 9
			[7,2,4,5,3,1,6,8,9] | 10
	}
}