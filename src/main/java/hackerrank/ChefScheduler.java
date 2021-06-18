package hackerrank;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

final public class ChefScheduler {
  	private static Integer getHoursWorked(Map<String, Integer> workingHoursPerWeek, String chef) {
		if(!workingHoursPerWeek.containsKey(chef)) {
			workingHoursPerWeek.put(chef, Integer.valueOf(0));
		}
		return workingHoursPerWeek.get(chef);
	}
  
    public static boolean schedulable(
        HashMap<String, ArrayList<String>> requests
    ) {
    	Map<String, Integer> daysOfWeek = new HashMap<>();
    	daysOfWeek.put("mon", 0);
    	daysOfWeek.put("tue", 1);
    	daysOfWeek.put("wed", 2);
    	daysOfWeek.put("thu", 3);
    	daysOfWeek.put("fri", 4);
    	daysOfWeek.put("sat", 5);
    	daysOfWeek.put("sun", 6);
    	
    	Map<String, Integer> workingHoursPerWeek = new HashMap<>();
    	
    	Set<String> chefs = requests.keySet();   
    	
    	chefs.stream().forEach(chef -> {
    		getHoursWorked(workingHoursPerWeek,  chef);
    	});
    	
        Map<Integer, Set<String>> chefsRequestsDayOff = new LinkedHashMap<>();
        for(int i=0; i<7; i++) chefsRequestsDayOff.put(i, new LinkedHashSet<>());
        
        for(Entry<String, ArrayList<String>> entry: requests.entrySet()) {
        	List<String> requestedDays = entry.getValue();
        	
        	for(int i=0; i<requestedDays.size(); i++) {
        		int dayIndex = daysOfWeek.get(requestedDays.get(i));
        		//Add chef to the calendar of days off
        		chefsRequestsDayOff.get(dayIndex).add(entry.getKey());
        	}
        }
        
        for(int i=0; i<7; i++) {
        	Set<String> chefAtDayOff = chefsRequestsDayOff.get(i);
    		List<Entry<String, Integer>> entryCandidates = workingHoursPerWeek.entrySet().stream()
    				.filter(entry -> {
    					return (entry.getValue() < 40) && !chefAtDayOff.contains(entry.getKey());
    				})
    				.sorted((o1, o2) -> Integer.valueOf(requests.get(o2.getKey()).size()).compareTo(requests.get(o1.getKey()).size()))
    				.sorted((o1, o2)-> o1.getValue().compareTo(o2.getValue()))
    				.collect(Collectors.toList());
    		if(!entryCandidates.isEmpty() && entryCandidates.size() > 1) {
    			for(int j=0; j<2; j++) {
    				Entry<String, Integer> candidate = entryCandidates.get(j);
    				candidate.setValue(candidate.getValue() + 8);
    			}
    		} else {
    			return false;
    		}
        }
        return true;
    }
}