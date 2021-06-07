package hackerank

import spock.lang.Specification

class MissingNumberTest extends Specification {
	def "one plus one should equal two" () {
		given:
			int[] input =  [3, 7, 1, 2, 8, 4, 5]
		when:
			int calculatedMissingResult = MissingNumber.find(input);
		then:
			calculatedMissingResult == 6
	}
}