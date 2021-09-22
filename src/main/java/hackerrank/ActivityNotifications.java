package hackerrank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ActivityNotifications {
    public static double getMedian(int d, int[] data) {
        double median = 0;
        if (d % 2 == 0) {
            Integer m1 = null;
            Integer m2 = null;
            int occurrences = 0;
            for (int expenditure = 0; expenditure < data.length; expenditure++) {
                occurrences += data[expenditure];
                if (m1 == null && occurrences >= d / 2) {
                    m1 = expenditure;
                }
                if (m2 == null && occurrences >= d / 2 + 1) {
                    m2 = expenditure;
                    break;
                }
            }
            median = (m1 + m2) / 2.0;
        } else {
            int occurrences = 0;
            for (int expenditure = 0; expenditure < data.length; expenditure++) {
                occurrences += data[expenditure];
                if (occurrences > d / 2) {
                    median = expenditure;
                    break;
                }
            }
        }
        return median;
    }

    public static int activityNotifications(List<Integer> expenditure, int d) {
        int notificationCount = 0;
        /*
         Its a principle of a count sort algorithm, you need to understand court sort algorithm
         data has the length of all possible values for expenditure
        * */
        int[] dataOccurrences = new int[201];
        for (int i = 0; i < d; i++) {
            dataOccurrences[expenditure.get(i)]++;
        }

        for (int begin = d; begin < expenditure.size(); begin++) {

            double median = getMedian(d, dataOccurrences);

            if (expenditure.get(begin) >= 2 * median) {
                notificationCount++;
            }
            /*
            * Add the next element int he count sort array
            * */
            dataOccurrences[expenditure.get(begin)]++;
            /*
            * Remove the first element that is not part of the range d
            * */
            dataOccurrences[expenditure.get(begin - d)]--;

        }
        System.out.println(notificationCount);
        return notificationCount;
    }
}

//public class ActivityNotifications {
//
//    public static int activityNotifications(List<Integer> expenditure, int d) {
//        int notificationCount = 0;
//
//        for(int i=d; i < expenditure.size(); i++){
//            List<Integer> trailing = expenditure.subList(i-d, i);
//            Collections.sort(trailing);
//            double median = 0.0;
//            if(d % 2 == 0) {
//                int m1 = trailing.get(d / 2);
//                int m2 = trailing.get((d / 2 ) + 1);
//                median = m1 + m2 / 2.0;
//            } else {
//                median = trailing.get(d / 2);
//            }
//            System.out.println("Value: " + expenditure.get(i) + " Median: 2x(" + median + ") = " + (2.0 * median) );
//            if(expenditure.get(i) >= (median * 2.0)) {
//                notificationCount++;
//            }
//        }
//        System.out.println(notificationCount);
//        return notificationCount;
//    }
//}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("c:/Java/input05.txt")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int d = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> expenditure = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = ActivityNotifications.activityNotifications(expenditure, d);
    }
}

