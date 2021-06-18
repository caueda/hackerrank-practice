package hackerrank;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class ChefSchedulerTest {
    
    @Test
    public void itShouldWorkOnBasicExamples() {
        HashMap<String, ArrayList<String>> requests = new HashMap<>();
        requests.put(
            "ada", new ArrayList<>(Arrays.asList(
                        "mon", "tue", "wed", "fri", "sat", "sun"))
        );
        requests.put(
            "emma", new ArrayList<>(Arrays.asList(
                        "tue", "wed", "thu", "sun"))
        );
        requests.put(
            "remy", new ArrayList<>(Arrays.asList(
                        "mon", "sat"))
        );
        requests.put(
            "zach", new ArrayList<>(Collections.emptyList())
        );
        assertTrue(ChefScheduler.schedulable(requests));
        
        requests.clear();
        requests.put(
            "emma", new ArrayList<>(Arrays.asList(
                        "sun"))
        );
        requests.put(
            "remy", new ArrayList<>(Arrays.asList(
                        "sun"))
        );
        requests.put(
            "zach", new ArrayList<>(Collections.emptyList())
        );
        assertFalse(ChefScheduler.schedulable(requests));
    
        requests.clear();
        requests.put(
            "ada", new ArrayList<>(Arrays.asList(
                        "mon", "tue", "wed", "thu", "fri", "sat"))
        );
        requests.put(
            "emma", new ArrayList<>(Arrays.asList(
                        "tue", "wed", "thu", "sun"))
        );
        requests.put(
            "remy", new ArrayList<>(Arrays.asList(
                        "mon", "fri", "sun"))
        );
        requests.put(
            "zach", new ArrayList<>(Collections.emptyList())
        );
        assertFalse(ChefScheduler.schedulable(requests));
    }
}
