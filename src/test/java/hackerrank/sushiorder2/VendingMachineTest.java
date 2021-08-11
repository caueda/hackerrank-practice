package hackerrank.sushiorder2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {
    @DisplayName("Test get Change")
    @ParameterizedTest
    @MethodSource("getArgs")
    public void testGetChange(int[] expected, BigDecimal givenMoney, BigDecimal productValue) {
        assertArrayEquals(expected, VendingMachine.getChange(givenMoney, productValue));
    }

    public static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of(new int[]{1,0,0,0,4}, BigDecimal.valueOf(5).setScale(2), BigDecimal.valueOf(2.99).setScale(2)),
                Arguments.of(new int[]{1,0,0,0,0}, BigDecimal.valueOf(5).setScale(2), BigDecimal.valueOf(4.99).setScale(2))
        );
    }
}