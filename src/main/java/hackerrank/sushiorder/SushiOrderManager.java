package hackerrank.sushiorder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class SushiOrderManager {
	
	TreeMap<Integer, Queue<SushiItem>> mapOrdersBySeats;
	int currentSeat = -1;
	int lastSeatServed;
	
	public SushiOrderManager() {
		/* initialize class state */
		this.mapOrdersBySeats = new TreeMap<>();
	}

	public void addOrder(ArrayList<SushiOrder> request) {	
		request.stream().forEach(sushiOrder -> {
			int seatId = sushiOrder.getSeatId();
			
			if(!mapOrdersBySeats.containsKey(seatId)) {
				mapOrdersBySeats.put(seatId, new LinkedList<SushiItem>());
			}
			sushiOrder.getMenuItemIds().stream().forEach(sushi -> {
				mapOrdersBySeats.get(seatId).offer(new SushiItem(seatId, sushi));
			});
		});
	}
	
	public SushiItem next() {
		if(currentSeat == -1) return null;
		SushiItem sushiItem = mapOrdersBySeats.get(currentSeat).poll();
		if(mapOrdersBySeats.get(currentSeat).isEmpty()) {
			mapOrdersBySeats.remove(currentSeat);
		}
		if(sushiItem == null && getOrderQuantity() > 0) {
			currentSeat = getNextSeat();
			return next();
		} 
		if(sushiItem != null) {
			lastSeatServed = currentSeat;
		}
		return sushiItem;
	}
	
	private int getNextSeat() {
		List<Integer> seats = new ArrayList<>(mapOrdersBySeats.keySet());
		if(seats.isEmpty()) return -1;
		int indexOfCurrentSeat = seats.indexOf(currentSeat);
		if(indexOfCurrentSeat == - 1) {
			
			for(int j=0; j<seats.size(); j++) {
				if(currentSeat < seats.get(j)) 
					return seats.get(j);
			}
			return seats.get(0);
		}
		int nextSeat = -1;
		if(indexOfCurrentSeat < seats.size()-1) {
			nextSeat = seats.get(indexOfCurrentSeat + 1);
			return nextSeat;
		} else {
			return seats.get(0);
		}
	}
	
	public SushiItem nextItem() {
		if(mapOrdersBySeats.isEmpty()) return null;
		if(currentSeat == -1) {
			currentSeat = mapOrdersBySeats.keySet().stream().findFirst().get();
		} else {
			currentSeat = getNextSeat();
		}
		if(lastSeatServed != 0 && lastSeatServed == currentSeat) {
			currentSeat = getNextSeat();
		}
		if(getOrderQuantity() == 0) return null;
		// return next roll object in the format
		SushiItem sushiItem = next();
		// new SushiItem(/*seatId*/ 1, /*menuItemId*/ "tuna");
		// or null if no order is pending
		return sushiItem;
	}
	
	private Integer getOrderQuantity() {
		return
				(int) mapOrdersBySeats.values().stream().flatMap(q -> q.stream()).count();
	}
}
