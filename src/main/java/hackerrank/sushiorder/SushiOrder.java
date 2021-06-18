package hackerrank.sushiorder;

import java.util.ArrayList;

public class SushiOrder {
	private int seatId;
	private ArrayList<String> menuItemIds;
	
	public SushiOrder(int seatId, ArrayList<String> menuItemIds) {
		this.seatId = seatId;
		this.menuItemIds = menuItemIds;
	}
	
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	
    public int getSeatId() {
    	return this.seatId;
    }
    
    public void setMenuItemIds(ArrayList<String> menuItemIds) {
    	this.menuItemIds = menuItemIds;
    }
    
    public ArrayList<String> getMenuItemIds() {
    	return this.menuItemIds;
    }

	@Override
	public String toString() {
		return "SushiOrder [seatId=" + seatId + ", menuItemIds=" + menuItemIds + "]";
	}
}
