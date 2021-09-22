package hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ActivityNotificationsTest {

    @Test
    void activityNotifications() {
        List<Integer> expenditure = Stream.of(10, 20, 30, 40, 50).collect(Collectors.toList());
        int d = 3;
        int result = ActivityNotifications.activityNotifications(expenditure, d);
        int expected = 1;
        Assertions.assertEquals(expected, result);
    }

    @Test
    void activityNotifications2() {
        List<Integer> expenditure = Stream.of(1, 2, 3, 4, 4).collect(Collectors.toList());
        int d = 4;
        int result = ActivityNotifications.activityNotifications(expenditure, d);
        int expected = 0;
        Assertions.assertEquals(expected, result);
    }

    @Test
    void activityNotifications3() {
        List<Integer> expenditure = Stream.of(2, 3, 4, 2, 3, 6, 8, 4, 5).collect(Collectors.toList());
        int d = 5;
        int result = ActivityNotifications.activityNotifications(expenditure, d);
        int expected = 2;
        Assertions.assertEquals(expected, result);
    }

//    @Test
//    void getMedian() {
//        double expected = 2.0;
//        int d = 7;
//        int[] data = new int[201];
//        data[1] = 2;
//        data[2] = 2;
//        data[4] = 1;
//        data[5] = 1;
//        data[7] = 1;
//        assertEquals(expected, ActivityNotifications.getMedian(d, data));
//    }
}