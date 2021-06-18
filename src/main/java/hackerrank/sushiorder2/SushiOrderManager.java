package hackerrank.sushiorder2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


class SushiOrderManager {
    private int totalSeats;
    private ArrayList<Queue<SushiItem>> seats;
    private LinkedList<LinkedList<Integer>> groups;
    private int currentGroupIndex = -1;
    private int currentGroupElementIndex = -1;
    private int seatWasServedInThisRound;

    private LinkedList<Integer> nextGroup = new LinkedList<>();
    
    public SushiOrderManager() {
        totalSeats = 16;
        seats = new ArrayList<>();
        groups = new LinkedList<>();
        for (int i = 0; i < totalSeats; i++) {
            seats.add(new LinkedList<SushiItem>());
        }
    }
    
    public void addOrder(ArrayList<SushiOrder> request) {
    	LinkedList<Integer> group = new LinkedList<>();
        for (SushiOrder order : request) {
        	if(currentGroupIndex != -1 && groups.get(currentGroupIndex).contains(order.getSeatId() - 1)) {
        		if(seats.get(order.getSeatId() - 1).isEmpty()) {
        			groups.get(currentGroupIndex).remove(groups.get(currentGroupIndex).indexOf(order.getSeatId() - 1));
        		}
        	}
        	if(currentGroupIndex == -1 || !groups.get(currentGroupIndex).contains(order.getSeatId() - 1)) {
        		group.add(order.getSeatId() - 1);
        	}
            for (String roll : order.getMenuItemIds()) {
                seats.get(order.getSeatId() - 1).offer(
                    new SushiItem(order.getSeatId(), roll)
                );
            }
        }
        if(!group.isEmpty()) {
        	Collections.sort(group);
        	groups.offer(group);
        }
    }
  
    public SushiItem nextItem() {
    	int seat = getNextSeat();
    	if(seat == -1) {
    		return null;
    	}
    	
    	for(int m=0; m<currentGroupIndex; m++) {
    		for(int s=0; s<groups.get(m).size(); s++) {
    			if(groups.get(m).get(s) == seat && !seats.get(seat).isEmpty()) {
    				seatWasServedInThisRound++;
    				break;
    			}
    		}
    	}
    	
    	SushiItem nextSushiItem = null;
    	
    	if(seatWasServedInThisRound != 1) {
    		nextSushiItem = seats.get(seat).poll();
    	} else {
    		seatWasServedInThisRound = 0;
    		return nextItem();
    	}
    	
    	
    	if(seats.get(seat).isEmpty()) {
    		boolean isAllGroupRoolsServed = true;
    		for(int i=0; i<groups.get(currentGroupIndex).size(); i++) {
    			if(!seats.get(groups.get(currentGroupIndex).get(i)).isEmpty()) {
    				isAllGroupRoolsServed = false;
    				break;
    			}
    		}
    		if(isAllGroupRoolsServed) {
	    		groups.remove(currentGroupIndex);
	    		
	    		if(!nextGroup.isEmpty()) {
	    			nextGroup.poll();
	    		} else {
	    			currentGroupIndex = -1;
	    		}
	    		
	    		currentGroupElementIndex = -1;
    		}
    	}
    	return nextSushiItem;
    }
    
    private int getNextSeat() {
    	if(groups.isEmpty()) {
    		return -1;
    	}
    	if(currentGroupIndex == -1) {
	    	currentGroupIndex = 0;
	    	currentGroupElementIndex = 0;
    	} else {
    		boolean isThereStillRollsToServe = false;
    		int totalVerificar = groups.get(currentGroupIndex).size();
    		while(totalVerificar > 0) {
    			if(currentGroupElementIndex + 1 < groups.get(currentGroupIndex).size()) {
    				//inside a round
    				if(nextGroup.isEmpty()) {
	    				if(currentGroupIndex + 1 < groups.size()) {//there is a new order group
	    					nextGroup.offer(currentGroupIndex + 1);
	    				} 
    				}
    				currentGroupElementIndex++;
    			} else {
    				if(nextGroup.isEmpty()) {
	    				if(currentGroupIndex + 1 < groups.size()) {
	    					nextGroup.offer(currentGroupIndex + 1);
	    				} 
    				} else {
    					currentGroupIndex = nextGroup.poll();
    					currentGroupElementIndex = 0;
    					if(nextGroup.isEmpty()){
	    					if(currentGroupIndex + 1 < groups.size()) {
		    					nextGroup.offer(currentGroupIndex + 1);
		    				} else {
		    					nextGroup.offer(0);
		    				}
    					}
    				}
    				boolean noMoreRollsInActualGroup = true;
    				for(int j=0; j<groups.get(currentGroupIndex).size(); j++) {
    					if(!seats.get(groups.get(currentGroupIndex).get(j)).isEmpty()) {
    						noMoreRollsInActualGroup = false;
    						break;
    					} 
    				}
    				if(noMoreRollsInActualGroup) {
    					if(currentGroupIndex + 1 < groups.size()) {
    						currentGroupIndex++;
    					} else {
    						currentGroupIndex = -1;
    						currentGroupElementIndex = -1;
    						return -1;
    					}
    				} 
    				currentGroupElementIndex = 0;
    			}
    			if(!seats.get(groups.get(currentGroupIndex).get(currentGroupElementIndex)).isEmpty()) {
    				isThereStillRollsToServe = true;
    				return groups.get(currentGroupIndex).get(currentGroupElementIndex);
    			} 
    			totalVerificar--;
    		}
    		if(!isThereStillRollsToServe) {
    			if(groups.isEmpty()) {
    				return -1;
    			} else {
    				if(currentGroupIndex + 1 < groups.size()) {
	    				currentGroupIndex++;
    				} else {
    					currentGroupIndex = -1;
    				}
    				currentGroupElementIndex = -1;
    				return getNextSeat();
    			}
    		}
    	}
    	return groups.get(currentGroupIndex).get(currentGroupElementIndex);
    }
}
