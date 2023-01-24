package hackerrank;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    @Test
    public void testSort(){
        int[] data = { 8, 7, 2, 1, 0, 9, 6 };
        QuickSort.sort(data, 0, data.length-1);
        Arrays.asList(data).stream().forEach(System.out::println);
        assertArrayEquals(new int[]{0, 1, 2, 6, 7, 8, 9}, data);
    }
}